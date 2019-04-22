package com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.adapter.AdapterCallBack
import com.cheiseproj.bik_krl.personalkotlin.adapter.ImageAdapter
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseActivity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.ImagePathProvider
import com.cheiseproj.bik_krl.personalkotlin.viewmodel.DiaryViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_add_or_update.*
import kotlinx.android.synthetic.main.dialog_save.view.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

private const val REQUEST_GALLERY:Int = 100
private const val REQUEST_STORAGE_PERMISSION = 101
class AddOrUpdateActivity : BaseActivity(),HasSupportFragmentInjector,AdapterCallBack<Int> {
    @Inject lateinit var fragmentInjector:DispatchingAndroidInjector<Fragment>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel:DiaryViewModel
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private var categories = ArrayList<String>()
    private var galleryPhotos = ArrayList<String>()
    private var photoList = ArrayList<PhotosEntity>()
    @Inject lateinit var imagePathProvider: ImagePathProvider
    private lateinit var imageAdapter:ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_update)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Create"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        imageAdapter = ImageAdapter(this)
        imageAdapter.setAdapterCallBack(this)
        recycler_view.layoutManager = GridLayoutManager(this,3)
        recycler_view.setHasFixedSize(true)
        configureViewModel()
        receiveSharedData()
    }


    private fun receiveSharedData() {
        if (intent != null){
            val receivedIntent = intent
            val receivedAction:String? = intent.action
            val receivedType:String? = intent.type
            if (receivedAction != null){
                if (receivedAction == Intent.ACTION_SEND){
                    if (receivedType?.startsWith("text/")!!){
                        Timber.i("Receiving Shared Data")
                        val data:String? = receivedIntent.getStringExtra(Intent.EXTRA_TEXT)
                        data?.let {ed_content.setText(it) }
                    }
                }
            }
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_or_update_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.nav_save -> saveDiary()
            R.id.nav_add_photo -> requestForPermission()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickEvent(value: Int) {

    }

    override fun onHoldEvent(value: Int) {
        setRemovePhotoDialog(value)
    }

    private fun setRemovePhotoDialog(position: Int) {
        Timber.i("Current selected item: ${galleryPhotos[position]}")
        val alertRemove = AlertDialog.Builder(this)
        alertRemove.setTitle("Delete Alert")
        alertRemove.setMessage(getString(R.string.delete_photo_message))
        alertRemove.setPositiveButton("remove") { dialog, _ ->
            galleryPhotos.removeAt(position)
            viewModel.galleryLiveImage.postValue(galleryPhotos)
            Timber.i("Current selected item removed")
            dialog.dismiss()
        }
        alertRemove.setCancelable(false)
        alertRemove.setNegativeButton("Cancel",null)
        alertRemove.show()
    }

    private fun pickImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, REQUEST_GALLERY)
    }

    private fun saveDiary() {
        savingDialog()
    }

    private fun savingDialog(){
        val content = ed_content.text.toString()
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_single_choice, categories)
        if (content.isEmpty()) { toastMessage(getString(R.string.content_empty_prompt));return}
        val builder = AlertDialog.Builder(this)
        val viewGroup = null
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_save,viewGroup,false)
        builder.setView(view)
        val alert = builder.create()
        view.dialog_title.text = getString(R.string.dialog_save_prompt)
        view.fab_cancel.setOnClickListener { alert.dismiss() }
        view.fab_save.setOnClickListener {saveUserDiaryToDb(alert,view,content) }
        view.spinner_category.adapter = arrayAdapter
        alert.show()
    }

    private fun saveUserDiaryToDb(alert: AlertDialog, view: View, content: String) {
        val title = view.ed_title.text.toString()
        if (title.isEmpty()) {toastMessage(getString(R.string.dialog_title_empty_prompt)); return }
        alert.dismiss()
        val category = view.spinner_category.selectedItem.toString()
        val diaryEntity = DiaryEntity(title,content,1,category, Date(), Date())
        viewModel.insertUserDiary(diaryEntity)
        viewModel.insertUserPhotos(galleryPhotos,getInsertDiaryId())
        finish()
    }

    private fun configureViewModel(){
       viewModel = ViewModelProviders.of(this,viewModelFactory).get(DiaryViewModel::class.java)
        viewModel.getDiaryCategory()
        getDiaryCategories()
        viewModel.galleryLiveImage.observe(this, Observer {
            galleryPhotos = it
            photoList.clear()
            it?.let { list -> list.forEach { dd -> photoList.add(PhotosEntity(dd,0,0))  } }
            Timber.i("Observe PhotoUrlList size: ${photoList.size}")
            imageAdapter.submitList(photoList)
            recycler_view.adapter = imageAdapter
        })
    }

    private fun getDiaryCategories(){
        viewModel.diaryCategory.observe(this, Observer {
            it.let { c -> c.forEach { data-> categories.add(data.title) } }
        } )
    }

    private fun getInsertDiaryId():Int{
        var diaryId = 0
        viewModel.diaryId.observe(this, Observer {
            diaryId = it
            Timber.i("inserted diaryId: $it")
        })
        return diaryId
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_GALLERY ->{
                if (resultCode == Activity.RESULT_OK && data != null){
                    getGalleryImage(data.data)
                }
            }
        }
    }

    private fun getGalleryImage(data: Uri?) {
        data?.let { galleryPhotos.add(imagePathProvider.getUriRealPath(it)) }
        viewModel.galleryLiveImage.postValue(galleryPhotos)
    }


    override fun supportFragmentInjector() = fragmentInjector

    //region Permission Request
    private fun requestForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Timber.i("Android Version: ${Build.VERSION.SDK_INT}")

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                Timber.i("Permission Denied")
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Timber.i("Permission Explained")
                    permissionDialogExplain()

                }else{
                    requestPermissions(permissions, REQUEST_STORAGE_PERMISSION)
                }

            }else{
                Timber.i("Permission Granted")
                pickImageFromGallery()
            }

        }else{
            Timber.i("Continue Without Checking Permission")
            pickImageFromGallery()
        }
    }

    private fun permissionDialogExplain() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Storage Permission Required")
        alert.setMessage(getString(R.string.storage_permission_explained))
        alert.setPositiveButton("Allow") { dialog, _ ->
            dialog.dismiss()
            val appIntent = Intent()
            appIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package",this.packageName,null)
            appIntent.data = uri
            startActivity(appIntent)
        }
        alert.setCancelable(false)
        alert.setNegativeButton("Deny",null)
        alert.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_STORAGE_PERMISSION ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Timber.i("Storage Permission Granted")
                    pickImageFromGallery()
                }
            }
        }
    }
    //endregion
}
