package com.cheiseproj.bik_krl.personalkotlin.di.module

import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module(includes = [PreferenceModule::class,RoomModule::class,RxJavaModule::class])
class AppModule {
    @Provides
    fun provideExecuter(): Executor {
         return Executors.newFixedThreadPool(2)
    }
}