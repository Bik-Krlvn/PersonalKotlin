package com.cheiseproj.bik_krl.personalkotlin.data.repository

import androidx.lifecycle.LiveData
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
): Repository<UserEntity>() {
    private var getUserEmail = ""
    private var getUserPassword = ""

    fun getAccountAuthentication(email:String,password:String){
        getUserEmail = email
        getUserPassword = password
    }

     fun checkUserEmail(email: String):Boolean{
        var isAvailable = false
        GlobalScope.launch(IO) {
            isAvailable = userDao.checkUserEmail(email)
        }
        Timber.i("checkUserEmail: $email exist $isAvailable")
        return isAvailable
    }

    override suspend fun insertDataAsync(data: UserEntity): Int {
        return withContext(IO){
            Timber.i("insertDataAsync: ${data.username} inserted successfully")
            return@withContext userDao.addNewUser(data).toInt()
        }
    }

    override suspend fun getDataByIdAsync(): LiveData<UserEntity> {
        return withContext(IO){
            Timber.i("getDataByIdAsync: Authenticating User")
            return@withContext userDao.getAccountAuthentication(getUserEmail,getUserPassword)
        }
    }

    //region UnUsed functions
    override suspend fun getDataAsync(): LiveData<List<UserEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertMultipleDataAsync(dataList: List<UserEntity>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateData(data: UserEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion




}