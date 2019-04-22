package com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.adapter.MainNavigationAdapter
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.settings.SettingsActivity
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.PersonalNavigationProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PersonalActivity : BaseActivity(),HasSupportFragmentInjector {
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.personal_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.nav_settings -> startActivity(Intent(this,SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}
