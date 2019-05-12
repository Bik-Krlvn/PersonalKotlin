package com.cheiseproj.bik_krl.personalkotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(){
    private val cachedUser:MediatorLiveData<AuthResource<UserEntity>> = MediatorLiveData()
    fun authenticateWith(source:LiveData<AuthResource<UserEntity>>){
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source){
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun getCachedUser():LiveData<AuthResource<UserEntity>> = cachedUser

//    fun logoutUser(){
//        cachedUser.value = AuthResource.logout()
//    }
}