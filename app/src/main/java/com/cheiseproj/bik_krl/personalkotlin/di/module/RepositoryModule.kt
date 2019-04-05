package com.cheiseproj.bik_krl.personalkotlin.di.module

import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.PhotoDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.repository.DiaryRepository
import com.cheiseproj.bik_krl.personalkotlin.data.repository.PhotoRepository
import com.cheiseproj.bik_krl.personalkotlin.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideDiaryRepository(diaryDao: DiaryDao):DiaryRepository{
        return DiaryRepository(diaryDao)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao):UserRepository{
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun providePhotoRepository(photoDao: PhotoDao):PhotoRepository{
        return PhotoRepository(photoDao)
    }
}