package com.cheiseproj.bik_krl.personalkotlin.di.module.data

import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.PhotoDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.repository.DiaryRepository
import com.cheiseproj.bik_krl.personalkotlin.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideDiaryRepository(diaryDao: DiaryDao,photoDao: PhotoDao) = DiaryRepository(diaryDao,photoDao)

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao) = AuthRepository(userDao)


}