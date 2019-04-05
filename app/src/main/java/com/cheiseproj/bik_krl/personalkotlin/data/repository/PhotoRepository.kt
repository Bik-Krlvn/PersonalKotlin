package com.cheiseproj.bik_krl.personalkotlin.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.PhotoDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val photoDao: PhotoDao
):Repository<PhotosEntity>() {
    
    private var getUserId = 0
    private var getDiaryId = 0

    fun getUserDiaryPhotos(userId:Int,diaryId:Int){
        getUserId = userId
        getDiaryId = diaryId
    }
    @WorkerThread override suspend fun getDataAsync(): LiveData<List<PhotosEntity>> {
        return withContext(IO){
            photoDao.getUserDiaryPhotos(getUserId,getDiaryId)
        }
    }

    override suspend fun getDataByIdAsync(): LiveData<PhotosEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertDataAsync(data: PhotosEntity): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @WorkerThread override suspend fun insertMultipleDataAsync(dataList: List<PhotosEntity>) {
       GlobalScope.launch(IO) {
           photoDao.insertUserDiaryPhotos(dataList)
       }
    }

    override suspend fun updateData(data: PhotosEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}