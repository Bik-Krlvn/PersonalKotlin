package com.cheiseproj.bik_krl.personalkotlin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.CategoryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface DiaryDao {
    //region Diary
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDiary(diaryEntity: DiaryEntity):Single<Long>

    @Query("SELECT * FROM table_diary WHERE userId = :userId ORDER BY id DESC ")
    fun getCurrentUserDiary(userId:Int):Flowable<List<DiaryEntity>>


    @Query("SELECT * FROM table_diary WHERE id = :diaryId AND userId = :userId ")
    fun getSpecifiedDiary(diaryId:Int,userId: Int):Flowable<List<DiaryEntity>>
    //endregion

    //region Category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDiaryCategory(vararg categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewCategory(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM table_category")
    fun getDiaryCategory():Flowable<List<CategoryEntity>>
    //endregion
}