package com.cheiseproj.bik_krl.personalkotlin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDiaryPhotos(photoList: ArrayList<PhotosEntity>):Single<List<Long>>

    @Query("SELECT * FROM table_photo WHERE diaryId = :diaryId AND userId = :userId ")
    fun getUserDiaryPhotos(diaryId:Int,userId: Int):Flowable<List<PhotosEntity>>

    @Query("SELECT * FROM table_photo WHERE userId = :userId")
    fun getAllUserPhotos(userId:Int):Flowable<List<PhotosEntity>>
}