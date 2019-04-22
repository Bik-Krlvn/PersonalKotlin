package com.cheiseproj.bik_krl.personalkotlin.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.utils.enums.ThemeMode
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.ThemeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity:AppCompatActivity(),CoroutineScope {
    @Inject lateinit var themeProvider: ThemeProvider
    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTheme()
        job = Job()



    }

    protected fun toastMessage(msg:String,lenght:Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,msg,lenght).show()
    }

    private fun setupTheme(){
        val themeMode= themeProvider.getThemeFromPreference()
        Timber.i("setupTheme: ${themeMode.name}")
        when(themeMode){
            ThemeMode.LIGHT -> setTheme(R.style.AppTheme)
            ThemeMode.DARK -> setTheme(R.style.AppDarkTheme)

        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
