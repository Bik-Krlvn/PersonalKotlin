package com.cheiseproj.bik_krl.personalkotlin.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cheiseproj.bik_krl.personalkotlin.data.DbTest
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.LiveDataUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest:DbTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewUserTest(){
        val newUserEntity = UserEntity("root","root@email.com","1234")
        val id = database.userDao().addNewUser(newUserEntity)
        assertThat(id,`is`(1L))
    }

    @Test
    fun getAccountAuthenticationTest(){
        val newUserEntity = UserEntity("root","root@email.com","1234")
        database.userDao().addNewUser(newUserEntity)
        val userEntity = LiveDataUtil.getValue(
            database.userDao().getAccountAuthentication(newUserEntity.email,newUserEntity.password))
        assertThat(userEntity.username,`is`("root"))
    }

    @Test
    fun checkUserEmailTest(){
        val newUserEntity = UserEntity("root","root@email.com","1234")
        database.userDao().addNewUser(newUserEntity)
        val isUserEmailExist = database.userDao().checkUserEmail(newUserEntity.email)
        assertThat(isUserEmailExist,`is`(true))
    }
}