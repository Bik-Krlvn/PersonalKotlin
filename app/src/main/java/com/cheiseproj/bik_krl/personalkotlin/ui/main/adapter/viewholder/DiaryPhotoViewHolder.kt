package com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter.viewholder

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cheiseproj.bik_krl.personalkotlin.adapter.AdapterCallBack
import com.cheiseproj.bik_krl.personalkotlin.adapter.PhotoPagerAdapter
import kotlinx.android.synthetic.main.photos_detail.view.*

class DiaryPhotoViewHolder constructor(itemView: View,adapterCallBack: AdapterCallBack<Int>) :
    RecyclerView.ViewHolder(itemView) {

    fun onBind(photoPagerAdapter: PhotoPagerAdapter?){
        if (photoPagerAdapter != null) {
            val height = TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200F, itemView.context.resources.displayMetrics)
            itemView.view_pager.layoutParams.height = height.toInt()
        }
        itemView.view_pager.adapter = photoPagerAdapter
    }

}