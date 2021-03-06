package com.cheiseproj.bik_krl.personalkotlin.di.module.preference

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.AccountProvider
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.ThemeProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {
    @Singleton
    @Provides
    fun provideSharedPreference(application: Application):SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Singleton
    @Provides
    fun provideThemeProvider(sharedPreferences: SharedPreferences):ThemeProvider{
        return ThemeProvider(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideAccountProvider(sharedPreferences: SharedPreferences):AccountProvider{
        return AccountProvider(sharedPreferences)
    }
}