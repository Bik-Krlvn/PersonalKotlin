package com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.adapter.AdapterCallBack
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.SimpleDateProvider
import kotlinx.android.synthetic.main.diary_details_about.view.*

class DiaryAboutViewHolder constructor(
    itemView:View,adapterCallBack: AdapterCallBack<Int>
) : RecyclerView.ViewHolder(itemView){


    fun onBind(diaryEntity: DiaryEntity?){
        itemView.tv_created_at.text =
            itemView.context.getString(
                R.string.detail_created_date
                , "${SimpleDateProvider.getLongDate(diaryEntity?.createdAt)}"
            )
        itemView.tv_modified_at.text = itemView.context.getString(
            R.string.detail_modified_date
            , SimpleDateProvider.getFormattedDate(diaryEntity?.modifiedAt?.time)
        )
        itemView.tv_category.text = itemView.context.getString(R.string.detail_category, diaryEntity?.category)

    }

}