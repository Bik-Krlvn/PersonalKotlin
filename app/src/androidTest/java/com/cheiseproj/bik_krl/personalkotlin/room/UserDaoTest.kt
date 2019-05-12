package com.cheiseproj.bik_krl.personalkotlin.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest :DbTest(){
    private lateinit var userDao: UserDao
    @Test
    fun insertNewUserAndAuthenticate(){
        userDao = db.userDao()
        val userEntity = UserEntity("user1","email@me.com","1234")
       userDao.addNewUser(userEntity).test().assertValue{
            it == 1L
        }
        userDao.getAccountAuthentication(userEntity.email,userEntity.password)
            .test().assertValue{
                it[0].id == 1 &&
                it[0].username ==  userEntity.username
            }
    }
}