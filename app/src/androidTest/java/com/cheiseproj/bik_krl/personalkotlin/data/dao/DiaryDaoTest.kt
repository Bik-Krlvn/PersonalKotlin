package com.cheiseproj.bik_krl.personalkotlin.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cheiseproj.bik_krl.personalkotlin.data.DbTest
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.LiveDataUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class DiaryDaoTest:DbTest() {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun insertUserDiaryTest(){
        val newDiaryEntity = DiaryEntity("diary","content",0,Date(), Date())
        val diaryId = database.diaryDao().insertUserDiary(newDiaryEntity)
        assertThat(diaryId,`is`(1L))
    }

    @Test
    fun getUserDiaryTest(){
        val userId = 1
        val newDiaryEntity = DiaryEntity("diary","content",userId,Date(), Date())
        database.diaryDao().insertUserDiary(newDiaryEntity)
        val diaryEntity = LiveDataUtil.getValue(database.diaryDao().getUserDiary(userId))
        assertThat(diaryEntity[0].content,`is`("content"))

    }

    @Test
    fun getUserDiaryByIdTest(){
        val userId = 1
        val newDiaryEntity = DiaryEntity("diary","content",userId,Date(), Date())
        val diaryId = database.diaryDao().insertUserDiary(newDiaryEntity).toInt()
        val diaryEntity = LiveDataUtil.getValue(database.diaryDao().getUserDiaryById(diaryId,userId))
        assertThat(diaryEntity.content,`is`("content"))

    }
}