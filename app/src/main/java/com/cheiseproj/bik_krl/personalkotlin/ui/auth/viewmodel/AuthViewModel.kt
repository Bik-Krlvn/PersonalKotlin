package com.cheiseproj.bik_krl.personalkotlin.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import com.cheiseproj.bik_krl.personalkotlin.data.repository.AuthRepository
import com.cheiseproj.bik_krl.personalkotlin.di.module.OBSERVER_ON
import com.cheiseproj.bik_krl.personalkotlin.di.module.SUBSCRIBER_ON
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.AuthResource
import com.cheiseproj.bik_krl.personalkotlin.viewmodel.BaseViewModel
import com.cheiseproj.bik_krl.personalkotlin.SessionManager
import io.reactivex.Scheduler
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class AuthViewModel @Inject constructor(
    private val userRepository: AuthRepository,
    @param:Named(SUBSCRIBER_ON) private val subscriberOn:Scheduler,
    @param:Named(OBSERVER_ON) private val observerOn:Scheduler,
    private val sessionManager: SessionManager
): BaseViewModel(){
    val userId: MutableLiveData<Int> = MutableLiveData()
    val errorAccount:MutableLiveData<Int> = MutableLiveData()

    fun addNewUser(userEntity: UserEntity){
       disposable.addAll(userRepository.checkEmailAddress(userEntity.email)
           .subscribeOn(subscriberOn)
           .observeOn(observerOn)
           .doOnError {
               Timber.i("user does not exist")
               insertUserDataToDb(userEntity)
           }
           .subscribe({errorAccount.value = R.string.error_account_exist},{}))
    }

    private fun insertUserDataToDb(userEntity: UserEntity) {
        disposable.addAll(userRepository.insertUser(userEntity)
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .doOnError { error-> Timber.i(error.message,"Failed to insert user") }
            .subscribe({
                    id -> Timber.i("user created id: $id")
                    userId.value = id.toInt()
            },{}))
    }

    fun checkEmail(email:String){
        disposable.addAll(userRepository.checkEmailAddress(email)
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .subscribe({errorAccount.value = R.string.error_account_exist},{})
        )
    }

    private fun queryUserCredentials(email: String, password:String):LiveData<AuthResource<UserEntity>>{
        return LiveDataReactiveStreams.fromPublisher(userRepository.authUser(email,password)
            .map {
                if (it.isEmpty()) return@map AuthResource.error("username or password incorrect",null)
                return@map AuthResource.authenticate(it[0])
            })
    }

    private fun queryUserId(userId:Int):LiveData<AuthResource<UserEntity>>{
        return LiveDataReactiveStreams.fromPublisher(userRepository.authUserWithId(userId)
            .map {
                if (it.isEmpty()) return@map AuthResource.error("user not found",null)
                return@map AuthResource.authenticate(it[0])
            })
    }

    fun authenticateWithId(userId: Int){
        sessionManager.authenticateWith(queryUserId(userId))
    }

    fun authenticateWithEmail(email: String,password: String){
        sessionManager.authenticateWith(queryUserCredentials(email,password))
    }

    fun authUserData():LiveData<AuthResource<UserEntity>> = sessionManager.getCachedUser()

}