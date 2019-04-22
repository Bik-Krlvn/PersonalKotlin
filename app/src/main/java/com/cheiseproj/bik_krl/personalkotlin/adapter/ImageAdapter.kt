package com.cheiseproj.bik_krl.personalkotlin.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.GlideApp
import kotlinx.android.synthetic.main.image_list.view.*
import java.io.File

class ImageAdapter constructor(
    private val context: Context)
    : ListAdapter<PhotosEntity, ImageAdapter.ViewHolder>(DiffTask()) {
    private lateinit var callBack: AdapterCallBack<Int>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context,getItem(position),callBack)
    }

    fun setAdapterCallBack(callBack: AdapterCallBack<Int>){
        this.callBack = callBack
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, item: PhotosEntity, callBack: AdapterCallBack<Int>) {
            val uri = Uri.parse(item.imagePath)
            val file = File(uri.path)
            if (file.exists()){
                GlideApp.with(context).load(file).centerCrop().into(itemView.image_view)
            }
            itemView.setOnClickListener {callBack.onClickEvent(adapterPosition)  }
            itemView.setOnLongClickListener { callBack.onHoldEvent(adapterPosition);true }
        }
    }

}

class DiffTask:DiffUtil.ItemCallback<PhotosEntity>(){
    override fun areItemsTheSame(oldItem: PhotosEntity, newItem: PhotosEntity): Boolean {
        return oldItem.imagePath == newItem.imagePath
    }

    override fun areContentsTheSame(oldItem: PhotosEntity, newItem: PhotosEntity): Boolean {
        return oldItem.imagePath == newItem.imagePath
    }

}