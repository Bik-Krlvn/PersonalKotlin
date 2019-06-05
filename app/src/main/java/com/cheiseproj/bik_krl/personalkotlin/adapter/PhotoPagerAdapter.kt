package com.cheiseproj.bik_krl.personalkotlin.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.GlideApp
import kotlinx.android.synthetic.main.image_list.view.*
import java.io.File
import java.util.*

class PhotoPagerAdapter : PagerAdapter() {
    private lateinit var photosEntities: List<PhotosEntity>

    override fun getCount(): Int {
        if (::photosEntities.isInitialized) {
            return photosEntities.size
        }
        return 0
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.image_list, container, false)
        if (::photosEntities.isInitialized) {
            if (photosEntities.isNotEmpty()) {
                val photosEntity = photosEntities[position]
                val uri = Uri.parse(photosEntity.imagePath)
                val file = File(Objects.requireNonNull(uri.path))
                if (file.exists()) {
                    GlideApp.with(container.context).load(file).fallback(R.drawable.placeholder).centerCrop()
                        .into(view.image_view)
                }
                container.addView(view)
            }
        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun setPhotos(photoList: List<PhotosEntity>) {
        this.photosEntities = photoList
        notifyDataSetChanged()
    }
}
