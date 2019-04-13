package com.cheiseproj.bik_krl.personalkotlin.data.repository

import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.CategoryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class DiaryRepository @Inject constructor(
    private val diaryDao: DiaryDao)
{

    fun getCurrentUserDiary(userId:Int):Flowable<List<DiaryEntity>>{
        return diaryDao.getCurrentUserDiary(userId)
    }

    fun insertUserDiary(diaryEntity: DiaryEntity):Completable{
        return Completable.fromAction{
            diaryDao.insertUserDiary(diaryEntity)
        }
    }

    fun getDiaryCategory():Flowable<List<CategoryEntity>>{
        return diaryDao.getDiaryCategory()
    }

}