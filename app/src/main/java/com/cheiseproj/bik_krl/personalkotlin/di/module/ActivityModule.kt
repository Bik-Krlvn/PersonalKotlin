package com.cheiseproj.bik_krl.personalkotlin.di.module

import com.cheiseproj.bik_krl.personalkotlin.SplashActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.activity.AuthActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.main.activity.diary.AddOrUpdateActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.main.activity.diary.DetailsActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.main.activity.diary.DiaryActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.settings.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributePersonalActivity(): DiaryActivity

    @ContributesAndroidInjector
    abstract fun contributeAccountActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeAddOrUpdateActivity(): AddOrUpdateActivity

    @ContributesAndroidInjector
    abstract fun  contributeDetailsActivity(): DetailsActivity
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity
    @ContributesAndroidInjector
    abstract fun contributeSettingsActivity():SettingsActivity
}