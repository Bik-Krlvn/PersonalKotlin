package com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.adapter.AdapterCallBack
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.CapSentencesProvider
import kotlinx.android.synthetic.main.diary_list.view.*

class DiaryAdapter : ListAdapter<DiaryEntity, DiaryAdapter.ViewHolder>(
    DiffTask()
) {
    private lateinit var callBack: AdapterCallBack<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.diary_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),callBack)
    }

    fun setAdapterCallBack(callBack: AdapterCallBack<Int>){
        this.callBack = callBack
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DiaryEntity?, callBack: AdapterCallBack<Int>) {
            itemView.tv_title.text = CapSentencesProvider.setValue(item?.title)
            itemView.tv_content.text = item?.content
            itemView.setOnClickListener { callBack.onClickEvent(item?.id!!) }
        }
    }

    private class DiffTask:DiffUtil.ItemCallback<DiaryEntity>(){
        override fun areItemsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
            return  oldItem.id          == newItem.id &&
                    oldItem.title       == newItem.title &&
                    oldItem.category    == newItem.category &&
                    oldItem.userId      == newItem.userId &&
                    oldItem.createdAt   == newItem.createdAt &&
                    oldItem.modifiedAt  == newItem.modifiedAt
        }
    }
}