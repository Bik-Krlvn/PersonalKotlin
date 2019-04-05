package com.cheiseproj.bik_krl.personalkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.fragment.DiaryFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.fragment.GalleryFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.fragment.TrashFragment

class MainNavigationAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> DiaryFragment()
            1 ->  GalleryFragment()
            else -> TrashFragment()
        }
    }

    override fun getCount() = 3
}