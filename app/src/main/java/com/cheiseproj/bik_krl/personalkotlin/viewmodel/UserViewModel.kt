package com.cheiseproj.bik_krl.personalkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import com.cheiseproj.bik_krl.personalkotlin.data.repository.UserRepository
import com.cheiseproj.bik_krl.personalkotlin.utils.delegate.lazyDeferred
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel() {
    var userEmail = ""
    var password = ""
    val checkUserEmail by lazyDeferred {
        userRepository.checkUserEmail(userEmail)
    }

    suspend fun addNewUser(userEntity: UserEntity):Int{
        val userId by lazyDeferred{
            userRepository.insertDataAsync(userEntity)
        }
        return userId.await()
    }

    val getAccountData by lazyDeferred{
        userRepository.getAccountAuthentication(userEmail,password)
    }
}