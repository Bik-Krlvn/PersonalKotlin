package com.cheiseproj.bik_krl.personalkotlin.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.activity.AuthActivity
import com.cheiseproj.bik_krl.personalkotlin.utils.enums.ThemeMode
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.AccountProvider
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.ThemeProvider
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.AuthStatus
import com.cheiseproj.bik_krl.personalkotlin.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity:DaggerAppCompatActivity(),CoroutineScope {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var themeProvider: ThemeProvider
    @Inject lateinit var accountProvider: AccountProvider
    @Inject lateinit var sessionManager: SessionManager
    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTheme()
        job = Job()
        observeAuthState()
    }


     fun setupTheme(){
        val themeMode= themeProvider.getThemeFromPreference()
        Timber.i("setupTheme: ${themeMode.name}")
        when(themeMode){
            ThemeMode.LIGHT -> setTheme(R.style.AppTheme)
            ThemeMode.DARK -> setTheme(R.style.AppDarkTheme)
        }
    }

    private fun observeAuthState(){
        sessionManager.getCachedUser().observe(this, Observer {
            it?.let {auth ->
                when(auth.status){
                    AuthStatus.LOADING -> Unit
                    AuthStatus.AUTHENTICATED -> {
                        Timber.i("welcome")
                    }
                    AuthStatus.ERROR -> Unit
                    AuthStatus.LOGOUT -> redirectToLoginPage()
                }
            }
        })
    }

    private fun redirectToLoginPage() {
        Timber.i("redirect")
        startActivity(intentFor<AuthActivity>().newTask().clearTask())
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
