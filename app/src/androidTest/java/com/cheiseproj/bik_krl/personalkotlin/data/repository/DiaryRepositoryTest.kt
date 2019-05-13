package com.cheiseproj.bik_krl.personalkotlin.data.repository

import com.cheiseproj.bik_krl.personalkotlin.DbTest
import com.cheiseproj.bik_krl.personalkotlin.data.db.dao.DiaryDao
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import org.junit.Test
import java.util.*

class DiaryRepositoryTest:DbTest() {
    private lateinit var diaryDao: DiaryDao
    private lateinit var diaryEntity: DiaryEntity

    override fun setUp() {
        super.setUp()
        diaryDao = db.diaryDao()
        diaryEntity = DiaryEntity("title","content",1,"test", Date(), Date())
    }

    @Test
    fun getCurrentUserDiary() {
        val diaryId = diaryDao.insertUserDiary(diaryEntity).test().values()[0].toInt()
        diaryDao.getCurrentUserDiary(diaryId).test().assertValue{
            it[0] == diaryEntity
        }
    }

    @Test
    fun insertUserDiary() {
        diaryDao.insertUserDiary(diaryEntity).test().assertValue{it == 1L}
    }

    @Test
    fun getDiaryCategory() {
        diaryDao.getDiaryCategory().test().assertValue{
            it.isEmpty()
        }
    }

    @Test
    fun insertUserDiaryPhoto() {
    }

    @Test
    fun getSpecifiedDiary() {
        val diaryId = diaryDao.insertUserDiary(diaryEntity).test().values()[0].toInt()
        diaryDao.getSpecifiedDiary(diaryId,diaryEntity.userId).test().assertValue{
            it == diaryEntity
        }
    }

    @Test
    fun getUserDiaryPhoto() {
    }
}