package com.cheiseproj.bik_krl.personalkotlin.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_diary")
data class DiaryEntity(
    val title:String,
    val content:String,
    val userId:Int,
    val category:String,
    val createdAt:Date,
    val modifiedAt:Date
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}