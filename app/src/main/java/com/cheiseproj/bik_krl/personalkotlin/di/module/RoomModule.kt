package com.cheiseproj.bik_krl.personalkotlin.di.module

import android.app.Application
import androidx.room.Room
import com.cheiseproj.bik_krl.personalkotlin.constant.DATABASE_NAME
import com.cheiseproj.bik_krl.personalkotlin.data.db.AppDatabase
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.PhotoDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class,ViewModelModule::class])
class RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(application: Application):AppDatabase{
        return Room.databaseBuilder(application,AppDatabase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideDiaryDao(database: AppDatabase):DiaryDao{
        return database.diaryDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase):UserDao{
        return database.userDao()
    }

    @Singleton
    @Provides
    fun providePhotoDao(database: AppDatabase):PhotoDao{
        return database.photoDao()
    }
}