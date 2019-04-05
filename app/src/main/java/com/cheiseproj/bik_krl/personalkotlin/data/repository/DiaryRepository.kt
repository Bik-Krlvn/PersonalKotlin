package com.cheiseproj.bik_krl.personalkotlin.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import kotlinx.coroutines.Dispatchers.IO
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


    @WorkerThread override suspend fun insertDataAsync(data: DiaryEntity):Int{
       return withContext(IO){
           Timber.i("insertData: ${data.title} success")
           return@withContext diaryDao.insertUserDiary(data).toInt()
       }
    }

    @WorkerThread override suspend fun getDataAsync(): LiveData<List<DiaryEntity>> {
        return withContext(IO){
            diaryDao.getUserDiary(getUserId)
        }
    }

    @WorkerThread override suspend fun getDataByIdAsync(): LiveData<DiaryEntity> {
        return withContext(IO){
            diaryDao.getUserDiaryById(getDiaryId,getUserId)
        }
    }

    override suspend fun insertMultipleDataAsync(dataList: List<DiaryEntity>) {
    }

    override suspend fun updateData(data: DiaryEntity) {
    }
}