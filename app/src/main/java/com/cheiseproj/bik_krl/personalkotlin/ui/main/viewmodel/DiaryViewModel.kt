package com.cheiseproj.bik_krl.personalkotlin.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.CategoryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.PhotosEntity
import com.cheiseproj.bik_krl.personalkotlin.data.repository.DiaryRepository
import com.cheiseproj.bik_krl.personalkotlin.di.module.OBSERVER_ON
import com.cheiseproj.bik_krl.personalkotlin.di.module.SUBSCRIBER_ON
import com.cheiseproj.bik_krl.personalkotlin.viewmodel.BaseViewModel
import com.cheiseproj.bik_krl.personalkotlin.vo.Resource
import io.reactivex.Scheduler
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository,
    @param:Named(SUBSCRIBER_ON) private val subscriberOn: Scheduler,
    @param:Named(OBSERVER_ON) private val observerOn: Scheduler

) : BaseViewModel() {
    val diaryCategory: MutableLiveData<List<CategoryEntity>> = MutableLiveData()
    val galleryLiveImage: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val diaryId: MutableLiveData<Int> = MutableLiveData()
    private val getCachedPhotos:MediatorLiveData<Resource<List<PhotosEntity>>> = MediatorLiveData()
    private val getCachedDiaries: MediatorLiveData<Resource<List<DiaryEntity>>> = MediatorLiveData()

    fun getDiaryCategory() {
        disposable.addAll(diaryRepository.getDiaryCategory()
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .subscribe { diaryCategory.value = it })
    }

    fun insertUserDiary(diaryEntity: DiaryEntity) {
        disposable.addAll(diaryRepository.insertUserDiary(diaryEntity)
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .doOnError { error -> Timber.i(error, "Data Insert Failed") }
            .subscribe { id -> diaryId.value = id.toInt(); Timber.i("Diary Inserted Successfully, Diary Id: $id"); })
    }

    fun insertUserPhotos(galleryPhotos: ArrayList<String>?, diaryId: Int, userId: Int) {
        galleryPhotos?.let { p ->
            disposable.addAll(diaryRepository.insertUserDiaryPhoto(p, userId, diaryId)
                .subscribeOn(subscriberOn)
                .observeOn(observerOn)
                .doOnError { error -> Timber.i(error, "Photos Insert Failed") }
                .subscribe { photoId -> Timber.i("Photos Inserted Successfully, Ids: $photoId") })
        }
    }

    fun getSpecifiedDiary(diaryId: Int, userId: Int): LiveData<Resource<List<DiaryEntity>>> {
        getCachedDiaries.value = Resource.loading(null)
        val source: LiveData<Resource<List<DiaryEntity>>> =
            LiveDataReactiveStreams.fromPublisher(
                diaryRepository.getSpecifiedDiary(diaryId, userId).map {
                    if (it.isEmpty()) return@map Resource.error("No content available", null)
                    return@map Resource.success(it)
                }
                    .subscribeOn(subscriberOn)
            )
        getCachedDiaries.addSource(source) {
            getCachedDiaries.value = it
            getCachedDiaries.removeSource(source)
        }
        return getCachedDiaries
    }

    fun getDiaryPhoto(diaryId: Int, userId: Int):LiveData<Resource<List<PhotosEntity>>> {
       getCachedPhotos.value = Resource.loading(null)
        val source:LiveData<Resource<List<PhotosEntity>>> = LiveDataReactiveStreams.fromPublisher(
            diaryRepository.getUserDiaryPhoto(diaryId,userId).map {
                if (it.isEmpty()){
                    return@map Resource.error("No photos found",null)
                }else{
                    return@map Resource.success(it)
                }
            }
                .subscribeOn(subscriberOn)
        )
        getCachedPhotos.addSource(source){
            getCachedPhotos.value = it
            getCachedPhotos.removeSource(source)
        }
        return getCachedPhotos
    }

    fun getUserDiary(userId: Int): LiveData<Resource<List<DiaryEntity>>> {
        getCachedDiaries.value = Resource.loading(null)
        val source: LiveData<Resource<List<DiaryEntity>>> = LiveDataReactiveStreams.fromPublisher(
            diaryRepository.getCurrentUserDiary(userId)
                .map {
                    if (it.isEmpty()) {return@map Resource.error("No Content Created", null)}else{
                        return@map Resource.success(it)

                    }
                }
                .subscribeOn(subscriberOn)
        )
        getCachedDiaries.addSource(source) {
            getCachedDiaries.value = it
            getCachedDiaries.removeSource(source)
        }
        return getCachedDiaries
    }

}