package com.cheiseproj.bik_krl.personalkotlin.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_photo")
data class PhotosEntity (
   val imagePath:String,
   val userId:Int,
   val diaryId:Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}