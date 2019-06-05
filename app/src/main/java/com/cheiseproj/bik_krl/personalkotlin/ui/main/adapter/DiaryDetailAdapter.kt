package com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.adapter.AdapterCallBack
import com.cheiseproj.bik_krl.personalkotlin.adapter.PhotoPagerAdapter
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter.viewholder.DiaryAboutViewHolder
import com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter.viewholder.DiaryDetailViewHolder
import com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter.viewholder.DiaryPhotoViewHolder

private const val DIARY_DETAIL_LAYOUT = 0
private const val DIARY_PHOTO_LAYOUT = 1
private const val DIARY_ABOUT_LAYOUT = 2

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var adapterCallBack: AdapterCallBack<Int>
    private var diaryEntity: DiaryEntity? = null
    private var photoPagerAdapter:PhotoPagerAdapter? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            DIARY_DETAIL_LAYOUT -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.diary_detail_content, parent, false)
                DiaryDetailViewHolder(parent.context, view, adapterCallBack)
            }
            DIARY_PHOTO_LAYOUT -> {

                view = LayoutInflater.from(parent.context).inflate(R.layout.photos_detail, parent, false)
                DiaryPhotoViewHolder(view, adapterCallBack)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.diary_details_about, parent, false)
                DiaryAboutViewHolder(view, adapterCallBack)

            }
        }
    }

    fun setAdapterCallBack(callBack: AdapterCallBack<Int>) {
        this.adapterCallBack = callBack
    }

    fun submitList(data: DiaryEntity) {
        this.diaryEntity = data
        notifyDataSetChanged()
    }

    fun addPhotoAdapter(adapter:PhotoPagerAdapter){
        this.photoPagerAdapter = adapter
        notifyDataSetChanged()
    }

    override fun getItemCount() = 3
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> DIARY_DETAIL_LAYOUT
            2 -> DIARY_PHOTO_LAYOUT
            1 -> DIARY_ABOUT_LAYOUT
            else -> -1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            DIARY_DETAIL_LAYOUT -> {
                (holder as DiaryDetailViewHolder).onBind(diaryEntity)
            }
            DIARY_ABOUT_LAYOUT -> {
                (holder as DiaryAboutViewHolder).onBind(diaryEntity)
            }

            DIARY_PHOTO_LAYOUT -> {
                (holder as DiaryPhotoViewHolder).onBind(photoPagerAdapter)
            }
        }

    }

}