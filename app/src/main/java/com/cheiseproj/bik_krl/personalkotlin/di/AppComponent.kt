package com.cheiseproj.bik_krl.personalkotlin.di

import android.app.Application
import com.cheiseproj.bik_krl.personalkotlin.PersonalApp
import com.cheiseproj.bik_krl.personalkotlin.di.module.ActivityModule
import com.cheiseproj.bik_krl.personalkotlin.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent
    }
    fun inject(personalApp: PersonalApp)
}