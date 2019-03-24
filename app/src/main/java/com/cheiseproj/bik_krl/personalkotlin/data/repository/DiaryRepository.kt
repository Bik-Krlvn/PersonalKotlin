package com.cheiseproj.bik_krl.personalkotlin.data.repository

import androidx.lifecycle.LiveData
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DiaryRepository @Inject constructor(
    private val diaryDao: DiaryDao):Repository<DiaryEntity>()
{
    private var getUserId = 0
    private var getDiaryId = 0

    fun getUserDiary(userId:Int){
        getUserId = userId
    }

    fun getUserDiaryById(diaryId:Int,userId: Int){
        getDiaryId = diaryId
        getUserId = userId
    }


    override suspend fun insertDataAsync(data: DiaryEntity):Int{
       return withContext(Dispatchers.IO){
           Timber.i("insertData: ${data.title} success")
           return@withContext diaryDao.insertUserDiary(data).toInt()
       }
    }

    override suspend fun getDataAsync(): LiveData<List<DiaryEntity>> {
        return withContext(Dispatchers.IO){
            diaryDao.getUserDiary(getUserId)
        }
    }

    override suspend fun getDataByIdAsync(): LiveData<DiaryEntity> {
        return withContext(Dispatchers.IO){
            diaryDao.getUserDiaryById(getDiaryId,getUserId)
        }
    }

    override suspend fun insertMultipleDataAsync(datalist: List<DiaryEntity>) {
    }

    override suspend fun updateData(data: DiaryEntity) {
    }
}