package com.cheiseproj.bik_krl.personalkotlin.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheiseproj.bik_krl.personalkotlin.di.key.ViewModelKey
import com.cheiseproj.bik_krl.personalkotlin.viewmodel.DiaryViewModel
import com.cheiseproj.bik_krl.personalkotlin.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @ViewModelKey(DiaryViewModel::class)
    @IntoMap
    abstract fun bindDiaryViewModel(diaryViewModel: DiaryViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory):ViewModelProvider.Factory


}