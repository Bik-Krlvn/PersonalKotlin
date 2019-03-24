package com.cheiseproj.bik_krl.personalkotlin.data

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cheiseproj.bik_krl.personalkotlin.data.db.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.TimeUnit

abstract class DbTest {
    lateinit var database: AppDatabase
    private val context = InstrumentationRegistry.getInstrumentation().context!!
    @JvmField
    @Rule val countingTaskExecutorRule = CountingTaskExecutorRule()
    @Before
    fun initDb(){
        database = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
    }

    @After
    fun closeDb(){
        countingTaskExecutorRule.drainTasks(10,TimeUnit.SECONDS)
        database.close()
    }
}