package com.cheiseproj.bik_krl.personalkotlin.di.module

import com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity.PersonalActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributePersonalActivity():PersonalActivity
}