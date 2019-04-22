package com.cheiseproj.bik_krl.personalkotlin.data.repository

import io.reactivex.Observable

abstract class Repository<T> {
    abstract fun getListData():Observable<List<T>>
    abstract fun getSingleData():Observable<T>

}