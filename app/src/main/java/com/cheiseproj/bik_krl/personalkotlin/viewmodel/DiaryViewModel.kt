package com.cheiseproj.bik_krl.personalkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.repository.DiaryRepository
import com.cheiseproj.bik_krl.personalkotlin.utils.delegate.lazyDeferred
import javax.inject.Inject

class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
):ViewModel() {

    var userId = 0
    var diaryId = 0

    val userDiary by lazyDeferred{
        diaryRepository.getUserDiary(userId)
        return@lazyDeferred diaryRepository.getDataAsync()
    }

    val userDiaryById by lazyDeferred{
        diaryRepository.getUserDiaryById(diaryId,userId)
        return@lazyDeferred diaryRepository.getDataByIdAsync()
    }

    suspend fun insertData(diaryEntity: DiaryEntity):Int{
        val  diaryId by lazyDeferred{
            diaryRepository.insertDataAsync(diaryEntity)
        }
        return diaryId.await()
    }

}