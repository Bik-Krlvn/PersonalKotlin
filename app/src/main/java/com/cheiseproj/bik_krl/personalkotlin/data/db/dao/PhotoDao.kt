package com.cheiseproj.bik_krl.personalkotlin.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDiaryPhotos(photoList: List<PhotosEntity>):List<Long>

    @Query("SELECT * FROM table_photo WHERE userId = :userId AND diaryId = :diaryId")
    fun getUserDiaryPhotos(userId:Int,diaryId:Int):LiveData<List<PhotosEntity>>

    @Query("SELECT * FROM table_photo WHERE userId = :userId")
    fun getAllUserPhotos(userId:Int):LiveData<List<PhotosEntity>>
}