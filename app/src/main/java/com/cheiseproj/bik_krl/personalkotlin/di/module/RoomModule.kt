package com.cheiseproj.bik_krl.personalkotlin.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cheiseproj.bik_krl.personalkotlin.constant.DATABASE_NAME
import com.cheiseproj.bik_krl.personalkotlin.data.db.AppDatabase
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.PhotoDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.UserDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.CategoryEntity
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class,ViewModelModule::class])
class RoomModule {
    private lateinit var appDatabase: AppDatabase
    @Singleton
    @Provides
    fun provideAppDatabase(application: Application):AppDatabase{
        appDatabase = Room.databaseBuilder(application,AppDatabase::class.java, DATABASE_NAME)
            .addCallback(object :RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor()
                        .execute {appDatabase.diaryDao().insertAllDiaryCategory(*CategoryEntity.populateCategory())
                    }
                }
            })
            .build()
        return appDatabase
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