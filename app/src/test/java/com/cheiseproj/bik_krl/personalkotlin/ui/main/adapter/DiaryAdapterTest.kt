package com.cheiseproj.bik_krl.personalkotlin.ui.main.adapter

import com.cheiseproj.bik_krl.personalkotlin.adapter.AdapterCallBack
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.DiaryEntity
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*
import kotlin.collections.ArrayList

@RunWith(RobolectricTestRunner::class)
class DiaryAdapterTest {
    private lateinit var adapterCallBack: AdapterCallBack<Int>
    private var diaryListEntity: ArrayList<DiaryEntity> = ArrayList()
    private lateinit var diaryAdapter: DiaryAdapter

    @Before
    fun setUp() {
        adapterCallBack = object :AdapterCallBack<Int>{
            override fun onClickEvent(value: Int) {

            }

            override fun onHoldEvent(value: Int) {
            }
        }
        diaryListEntity.apply {
            add(DiaryEntity("title1","content1",1,"personal", Date(), Date()))
            add( DiaryEntity("title2","content2",2,"work", Date(), Date()))
            add(DiaryEntity("title3","content3",3,"school", Date(), Date()))
        }
        diaryAdapter = DiaryAdapter()
    }

    @Test
    fun setItems(){
        diaryAdapter.submitList(diaryListEntity)
        assert(diaryAdapter.itemCount == 3)
    }

    @Test
    fun setCallBack(){
        assertNotNull(diaryAdapter.setAdapterCallBack(adapterCallBack))
    }
}