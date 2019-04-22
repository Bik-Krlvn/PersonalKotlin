package com.cheiseproj.bik_krl.personalkotlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.cheiseproj.bik_krl.personalkotlin.data.db.converter.DateTypeConverter
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.PhotoDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.CategoryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity

@Database(entities = [
    UserEntity::class,
    DiaryEntity::class,
    CategoryEntity::class,
    PhotosEntity::class],version = 1,exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun diaryDao():DiaryDao
    abstract fun photoDao():PhotoDao
}