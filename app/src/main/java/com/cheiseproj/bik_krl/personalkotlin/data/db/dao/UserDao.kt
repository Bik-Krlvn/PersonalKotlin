package com.cheiseproj.bik_krl.personalkotlin.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewUser(userEntity: UserEntity): Single<Long>

    @Query("SELECT email FROM table_user WHERE email = :email")
    fun checkUserEmail(email:String):Single<String>

    @Query("SELECT * FROM table_user WHERE email = :email AND password = :password LIMIT 1")
    fun getAccountAuthentication(email:String,password:String):Flowable<List<UserEntity>>

    @Query("SELECT * FROM table_user WHERE id = :userId LIMIT 1")
    fun getAccountAuthenticationWithId(userId:Int):Flowable<List<UserEntity>>

}