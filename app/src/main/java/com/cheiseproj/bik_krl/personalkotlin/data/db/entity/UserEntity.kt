package com.cheiseproj.bik_krl.personalkotlin.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
data class UserEntity (
    val username:String,
    val email:String,
    val password:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}