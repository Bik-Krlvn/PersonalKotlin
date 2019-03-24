package com.cheiseproj.bik_krl.personalkotlin.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDiary(diaryEntity: DiaryEntity):Long

    @Query("SELECT * FROM table_diary WHERE userId = :userId ")
    fun getUserDiary(userId:Int):LiveData<List<DiaryEntity>>


    @Query("SELECT * FROM table_diary WHERE id = :diaryId AND userId = :userId ")
    fun getUserDiaryById(diaryId:Int,userId:Int):LiveData<DiaryEntity>
}