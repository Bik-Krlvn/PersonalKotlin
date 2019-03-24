package com.cheiseproj.bik_krl.personalkotlin.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cheiseproj.bik_krl.personalkotlin.data.DbTest
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import com.cheiseproj.bik_krl.personalkotlin.utils.LiveDataUtil
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoDaoTest:DbTest() {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertUserDiaryPhotosTest(){
        val userId = 1
        val diaryId = 1
        val newDiaryPhotos = listOf(PhotosEntity("location//img.jpg",userId,diaryId))
        val photoIdList = database.photoDao().insertUserDiaryPhotos(newDiaryPhotos)
        assertThat(photoIdList[0],`is`(1L))
    }

    @Test
    fun getUserDiaryPhotosTest(){
        val userId = 1
        val diaryId = 1
        val newDiaryPhotos = listOf(PhotosEntity("location//img.jpg",userId,diaryId))
        database.photoDao().insertUserDiaryPhotos(newDiaryPhotos)
        val diaryPhotosEntity = LiveDataUtil.getValue(database.photoDao().getUserDiaryPhotos(userId,diaryId))
        assertThat(diaryPhotosEntity[0].imagePath,`is`("location//img.jpg"))
    }

    @Test
    fun getAllUserPhotosTest(){
        val userId = 1
        val diaryId = 1
        val newDiaryPhotos = listOf(PhotosEntity("location//img.jpg",userId,diaryId))
        database.photoDao().insertUserDiaryPhotos(newDiaryPhotos)
        val diaryPhotosEntity = LiveDataUtil.getValue(database.photoDao().getAllUserPhotos(userId))
        assertThat(diaryPhotosEntity[0].imagePath,`is`("location//img.jpg"))
    }


}