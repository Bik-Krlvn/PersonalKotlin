package com.cheiseproj.bik_krl.personalkotlin.data.repository

import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.PhotoDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.CategoryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class DiaryRepository @Inject constructor(
    private val diaryDao: DiaryDao,
    private val photoDao: PhotoDao)
{

    fun getCurrentUserDiary(userId:Int):Flowable<List<DiaryEntity>>{
        return diaryDao.getCurrentUserDiary(userId)
    }

    fun insertUserDiary(diaryEntity: DiaryEntity):Single<Long>{
        return diaryDao.insertUserDiary(diaryEntity)
    }

    fun getDiaryCategory():Flowable<List<CategoryEntity>>{
        return diaryDao.getDiaryCategory()
    }

    fun insertUserDiaryPhoto(photoList: ArrayList<String>,userId: Int,diaryId:Int):Single<List<Long>>{
        val photoEntityList = ArrayList<PhotosEntity>()
        photoList.forEach { p -> photoEntityList.add(PhotosEntity(p,userId,diaryId)) }
        return photoDao.insertUserDiaryPhotos(photoEntityList)
    }

}