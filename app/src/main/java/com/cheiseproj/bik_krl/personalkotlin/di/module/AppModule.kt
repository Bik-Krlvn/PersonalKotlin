package com.cheiseproj.bik_krl.personalkotlin.di.module

import android.app.Application
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.ImagePathProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [PreferenceModule::class,RoomModule::class,RxJavaModule::class])
class AppModule {
   @Singleton
   @Provides
   fun provideImagePathProvider(application: Application):ImagePathProvider{
       return ImagePathProvider(application.applicationContext)
   }
}