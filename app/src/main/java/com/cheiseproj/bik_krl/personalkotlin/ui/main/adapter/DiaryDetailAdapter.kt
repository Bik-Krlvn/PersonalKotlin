package com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.adapter.PhotoPagerAdapter
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.CapSentencesProvider
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.SimpleDateProvider
import kotlinx.android.synthetic.main.diary_detail_content.view.*
import kotlinx.android.synthetic.main.diary_details_about.view.*
import kotlinx.android.synthetic.main.photos_detail.view.*
import java.util.regex.Pattern

private const val DIARY_DETAIL_LAYOUT = 0
private const val DIARY_PHOTO_LAYOUT = 1
private const val DIARY_ABOUT_LAYOUT = 2
class DetailAdapter constructor(private val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var diaryEntity: DiaryEntity? = null
    private lateinit var photoPagerAdapter: PhotoPagerAdapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            DIARY_DETAIL_LAYOUT -> {
                DiaryDetailsVH(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.diary_detail_content, parent, false)
                )
            }
            DIARY_PHOTO_LAYOUT -> {
                DiaryPhotosVH(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.photos_detail, parent, false)
                )
            }
            else  -> {
                DiaryAboutVH(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.diary_details_about, parent, false)
                )
            }
        }
    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            DIARY_DETAIL_LAYOUT -> {
                (holder as DiaryDetailsVH).bind(diaryEntity)
            }
            DIARY_PHOTO_LAYOUT -> {
                (holder as DiaryPhotosVH).bind(photoPagerAdapter)
            }
            else -> {
                (holder as DiaryAboutVH).bind(context,diaryEntity)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> DIARY_DETAIL_LAYOUT
            1 -> DIARY_PHOTO_LAYOUT
            2 -> DIARY_ABOUT_LAYOUT
            else -> -1
        }
    }

    fun setDetail(diaryEntity: DiaryEntity){
        this.diaryEntity = diaryEntity
        notifyDataSetChanged()
    }
    fun setPhotosPagerAdapter(pagerAdapter: PhotoPagerAdapter){
        this.photoPagerAdapter = pagerAdapter
        notifyDataSetChanged()
    }
}

 class DiaryDetailsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(diaryEntity: DiaryEntity?) {
        itemView.tv_title.text = CapSentencesProvider.setValue(diaryEntity?.title)
        val hashTags = SpannableString(diaryEntity?.content)
        val match = Pattern.compile("#([A-Za-z0-9_-]+)").matcher(hashTags)
        while (match.find()){
            hashTags
                .setSpan(ForegroundColorSpan(Color.rgb(0, 120, 215)),
                    match.start(),match.end(),0)
        }
        itemView.tv_content.text = hashTags
    }
}

class DiaryPhotosVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(photoPagerAdapter: PhotoPagerAdapter) {
        if (photoPagerAdapter.count > 0){
            val height = TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP,200F,itemView.context.resources.displayMetrics)
            itemView.view_pager.layoutParams.height = height.toInt()
        }
        itemView.view_pager.adapter = photoPagerAdapter
    }
}

class DiaryAboutVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(context: Context,diaryEntity: DiaryEntity?) {
        itemView.tv_created_at.text =
            context.getString(R.string.detail_created_date
                ,"${SimpleDateProvider.getLongDate(diaryEntity?.createdAt)}")
        itemView.tv_modified_at.text =  context.getString(R.string.detail_modified_date
            , SimpleDateProvider.getFormattedDate(diaryEntity?.modifiedAt!!.time)
        )
        itemView.tv_category.text = context.getString(R.string.detail_category, diaryEntity.category)
    }
}