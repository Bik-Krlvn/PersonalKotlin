package com.cheiseproj.bik_krl.personalkotlin.di

import android.app.Application
import com.cheiseproj.bik_krl.personalkotlin.PersonalApp
import com.cheiseproj.bik_krl.personalkotlin.di.module.ActivityModule
import com.cheiseproj.bik_krl.personalkotlin.di.module.AppModule
import com.cheiseproj.bik_krl.personalkotlin.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class
])
interface AppComponent:AndroidInjector<PersonalApp> {
    fun sessionManager(): SessionManager
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent
    }

    override fun inject(instance: PersonalApp?)
}