package com.cheiseproj.bik_krl.personalkotlin.data.repository

import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class AuthRepository @Inject constructor (
    private val userDao: UserDao){
    fun insertUser(userEntity: UserEntity):Single<Long>{
        return userDao.addNewUser(userEntity)
    }

    fun checkEmailAddress(email:String):Single<String>{
        return userDao.checkUserEmail(email)
    }

    fun authUser(email: String,password:String):Flowable<List<UserEntity>>{
        return userDao.getAccountAuthentication(email,password)
    }

    fun authUserWithId(userId:Int):Flowable<List<UserEntity>>{
        return userDao.getAccountAuthenticationWithId(userId)
    }
}