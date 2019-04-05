package com.cheiseproj.bik_krl.personalkotlin.di.module

import com.cheiseproj.bik_krl.personalkotlin.ui.account.activity.AccountActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity.AddOrUpdateActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity.PersonalActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributePersonalActivity():PersonalActivity

    @ContributesAndroidInjector
    abstract fun contributeAccountActivity(): AccountActivity

    @ContributesAndroidInjector
    abstract fun contributeAddOrUpdateActivity():AddOrUpdateActivity
}