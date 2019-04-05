package com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.appbar.*
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
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController,null)
    //region Dagger Injection
    override fun supportFragmentInjector() = fragmentInjector
    //endregion


}
