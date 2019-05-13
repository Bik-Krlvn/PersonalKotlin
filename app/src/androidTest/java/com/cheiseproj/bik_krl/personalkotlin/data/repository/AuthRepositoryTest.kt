package com.cheiseproj.bik_krl.personalkotlin.data.repository

import com.cheiseproj.bik_krl.personalkotlin.DbTest
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import org.junit.Test

class AuthRepositoryTest: DbTest() {
    private lateinit var userDao: UserDao
    private lateinit var userEntity: UserEntity

    override fun setUp() {
        super.setUp()
        userDao = db.userDao()
        userEntity = UserEntity("user1","email@me.com","1234")
    }

    @Test
    fun insertUser() {
        userDao.addNewUser(userEntity).test().assertValue{
            it == 1L
        }
    }

    @Test
    fun checkEmailAddress() {
        userDao.addNewUser(userEntity).test()
        userDao.checkUserEmail(userEntity.email).test().assertValue{it == userEntity.email}
    }

    @Test
    fun authUser() {
        val userId = userDao.addNewUser(userEntity).test().values()
        userDao.getAccountAuthentication(userEntity.email,userEntity.password)
            .test().assertValue{it[0].id == userId[0].toInt()}
    }

    @Test
    fun authUserWithId() {
        val userId = userDao.addNewUser(userEntity).test().values()[0].toInt()
        userDao.getAccountAuthenticationWithId(userId).test().assertValue{it[0] == userEntity}
    }
}