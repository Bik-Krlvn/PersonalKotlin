package com.cheiseproj.bik_krl.personalkotlin.di.module

import com.cheiseproj.bik_krl.personalkotlin.ui.personal.fragment.DiaryFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.fragment.TrashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeDiaryFragment():DiaryFragment

    @ContributesAndroidInjector
    abstract fun contributeTrashFragment():TrashFragment
}