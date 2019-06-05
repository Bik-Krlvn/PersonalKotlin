package com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cheiseproj.bik_krl.personalkotlin.adapter.AdapterCallBack
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import kotlinx.android.synthetic.main.diary_detail_content.view.*

class DiaryDetailViewHolder constructor(context: Context, itemView: View, adapterCallBack: AdapterCallBack<Int>) :
    RecyclerView.ViewHolder(itemView) {

    fun onBind(diaryEntity: DiaryEntity?){
        itemView.tv_title.text = diaryEntity?.title
        itemView.tv_content.text = diaryEntity?.content
    }

}