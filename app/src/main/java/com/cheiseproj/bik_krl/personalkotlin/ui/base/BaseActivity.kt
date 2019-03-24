package com.cheiseproj.bik_krl.personalkotlin.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.utils.enums.ThemeMode
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.ThemeProvider
import kotlinx.android.synthetic.main.appbar.*
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity:AppCompatActivity() {
    @Inject lateinit var themeProvider: ThemeProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTheme()

    }

    private fun setupTheme(){
        val themeMode = themeProvider.getThemeFromPreference()
        Timber.i("setupTheme: ${themeMode.name}")
        when(themeMode){
            ThemeMode.LIGHT -> setTheme(R.style.AppTheme)
            ThemeMode.DARK -> setTheme(R.style.AppDarkTheme)
        }
    }
}