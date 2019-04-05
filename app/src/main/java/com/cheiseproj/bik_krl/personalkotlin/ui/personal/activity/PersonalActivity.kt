package com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.adapter.MainNavigationAdapter
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseActivity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.PersonalNavigationProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PersonalActivity : BaseActivity(),HasSupportFragmentInjector {
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        configureNavigation()
    }

    private fun configureNavigation() {
        view_pager.adapter = MainNavigationAdapter(supportFragmentManager)
        view_pager.offscreenPageLimit = 3
        PersonalNavigationProvider.setComponent(this,view_pager,main_bottom_nav)
    }

    //region Dagger Injection
    override fun supportFragmentInjector() = fragmentInjector
    //endregion


}
