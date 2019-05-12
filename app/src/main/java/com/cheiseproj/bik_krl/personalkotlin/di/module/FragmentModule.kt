package com.cheiseproj.bik_krl.personalkotlin.di.module


import com.cheiseproj.bik_krl.personalkotlin.ui.auth.fragment.LoginFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.fragment.RegisterFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.main.fragment.DiaryFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.main.fragment.TrashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeDiaryFragment(): DiaryFragment

    @ContributesAndroidInjector
    abstract fun contributeTrashFragment(): TrashFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment
}