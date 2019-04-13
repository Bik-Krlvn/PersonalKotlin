package com.cheiseproj.bik_krl.personalkotlin.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_category")
data class CategoryEntity (
    val title:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    companion object {
       fun populateCategory():Array<CategoryEntity>{
           return arrayOf(
               CategoryEntity("Personal"),
               CategoryEntity("Work"),
               CategoryEntity("School"),
               CategoryEntity("Church"),
               CategoryEntity("Others")
           )
       }
    }
}