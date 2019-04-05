package com.cheiseproj.bik_krl.personalkotlin.utils.provider

import android.content.SharedPreferences
import com.cheiseproj.bik_krl.personalkotlin.constant.KEY_PREF_DARK_THEME
import com.cheiseproj.bik_krl.personalkotlin.utils.enums.ThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThemeProvider  @Inject constructor(
    private val preference:SharedPreferences
){
     fun getThemeFromPreference():ThemeMode{
         val isDarkMode = preference.getBoolean(KEY_PREF_DARK_THEME,false)
         return if (isDarkMode) ThemeMode.DARK else ThemeMode.LIGHT
    }
}