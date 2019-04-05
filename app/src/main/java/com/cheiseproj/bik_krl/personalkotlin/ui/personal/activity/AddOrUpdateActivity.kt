package com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_addor_update.*
import javax.inject.Inject

class AddOrUpdateActivity : BaseActivity(),HasSupportFragmentInjector {
    @Inject lateinit var frgamentInjector:DispatchingAndroidInjector<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addor_update)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Create"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_or_update_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun supportFragmentInjector() = frgamentInjector

}
