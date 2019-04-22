package com.cheiseproj.bik_krl.personalkotlin.adapter

interface AdapterCallBack<T>{
    fun onClickEvent(value:T)
    fun onHoldEvent(value:T)
}