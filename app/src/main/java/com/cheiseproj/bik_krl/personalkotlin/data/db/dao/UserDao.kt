package com.cheiseproj.bik_krl.personalkotlin.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewUser(userEntity: UserEntity):Long

    @Query("SELECT * FROM table_user WHERE email = :email")
    fun checkUserEmail(email:String):Boolean

    @Query("SELECT * FROM table_user WHERE email = :email AND password = :password")
    fun getAccountAuthentication(email:String,password:String):LiveData<UserEntity>

}