package com.cheiseproj.bik_krl.personalkotlin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.cheiseproj.bik_krl.personalkotlin.data.db.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class DbTest {
    protected lateinit var db:AppDatabase
    @JvmField
    @Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    open fun setUp(){
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,AppDatabase::class.java)
            .build()
    }

    @After
    fun tearDown(){
        db.close()
    }
}