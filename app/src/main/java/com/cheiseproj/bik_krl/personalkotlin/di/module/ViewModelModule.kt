package com.cheiseproj.bik_krl.personalkotlin.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheiseproj.bik_krl.personalkotlin.di.key.ViewModelKey
import com.cheiseproj.bik_krl.personalkotlin.ui.main.viewmodel.DiaryViewModel
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.viewmodel.AuthViewModel
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
    @ViewModelKey(AuthViewModel::class)
    @IntoMap
    abstract fun bindUserViewModel(authViewModel: AuthViewModel):ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory):ViewModelProvider.Factory

}