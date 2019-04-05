package com.cheiseproj.bik_krl.personalkotlin.data.repository

import androidx.lifecycle.LiveData

abstract class Repository<T> {
    abstract suspend fun getDataAsync():LiveData<List<T>>
    abstract suspend fun getDataByIdAsync():LiveData<T>
    abstract suspend fun insertDataAsync(data:T):Int
    abstract suspend fun insertMultipleDataAsync(dataList:List<T>)
    abstract suspend fun updateData(data: T)

}