package com.cheiseproj.bik_krl.personalkotlin.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

abstract class Repository<T> {
    @WorkerThread
    abstract suspend fun getDataAsync():LiveData<List<T>>
    abstract suspend fun getDataByIdAsync():LiveData<T>
    @WorkerThread
    abstract suspend fun insertDataAsync(data:T):Int
    @WorkerThread
    abstract suspend fun insertMultipleDataAsync(datalist:List<T>)
    @WorkerThread
    abstract suspend fun updateData(data: T)

}