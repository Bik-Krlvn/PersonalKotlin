package com.cheiseproj.bik_krl.personalkotlin.utils.provider

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val KEY_ASK_ACCOUNT = "key_pref_ask_account"
class AccountProvider @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    suspend fun getLoginMode():LoginMode{
        return withContext(Dispatchers.IO){
            val isSkipMode = sharedPreferences.getBoolean(KEY_ASK_ACCOUNT,false)
            return@withContext if (!isSkipMode) LoginMode.SKIP_LOGIN else LoginMode.ASK_LOGIN
        }

    }
}

enum class LoginMode{
    ASK_LOGIN,SKIP_LOGIN
}