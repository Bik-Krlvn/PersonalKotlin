package com.cheiseproj.bik_krl.personalkotlin.utils.provider

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.cheiseproj.bik_krl.personalkotlin.R
import devlight.io.library.ntb.NavigationTabBar

object PersonalNavigationProvider {
    private fun getNavigationModels(context: Context):ArrayList<NavigationTabBar.Model>{
        val colors = context.resources.getStringArray(R.array.default_preview)
        val models = ArrayList<NavigationTabBar.Model>()
        models.add(NavigationTabBar.Model.Builder(
            ContextCompat.getDrawable(context,R.drawable.ic_open_book)
            ,Color.parseColor(colors[4])).title("Diary").build())
        models.add(NavigationTabBar.Model.Builder(
            ContextCompat.getDrawable(context,R.drawable.ic_photo_camera)
            ,Color.parseColor(colors[1])).title("Gallery").build())
        models.add(NavigationTabBar.Model.Builder(
            ContextCompat.getDrawable(context,R.drawable.ic_bin)
            ,Color.parseColor(colors[0])).title("Trash").build())

        return models
    }

    fun setComponent(context: Context,viewPager: ViewPager,navigationTabBar: NavigationTabBar){
        navigationTabBar.models = getNavigationModels(context)
        navigationTabBar.setViewPager(viewPager,0)
    }
}