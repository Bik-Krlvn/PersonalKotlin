package com.cheiseproj.bik_krl.personalkotlin

import android.os.Bundle
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.activity.AuthActivity
import com.cheiseproj.bik_krl.personalkotlin.base.BaseActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.main.activity.diary.DiaryActivity
import com.cheiseproj.bik_krl.personalkotlin.utils.delegate.lazyDeferred
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.LoginMode
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skipLogin()
    }
    private fun skipLogin()=launch{
        val skip by lazyDeferred {
            accountProvider.getLoginMode()
        }
        when(skip.await()){
            LoginMode.ASK_LOGIN -> {startActivity<AuthActivity>();finish()}
            LoginMode.SKIP_LOGIN -> loadDiary()
        }
    }

    private fun loadDiary(){
        startActivity<DiaryActivity>()
        finish()
    }

}
