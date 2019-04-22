package com.cheiseproj.bik_krl.personalkotlin.ui.account.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity.PersonalActivity
import com.cheiseproj.bik_krl.personalkotlin.utils.delegate.lazyDeferred
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.AccountProvider
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.LoginMode
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.account_appbar.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AccountActivity : BaseActivity(),HasSupportFragmentInjector {
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var accountProvider: AccountProvider
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setSupportActionBar(toolbar)
        skipLogin()
        configureNavigation()

    }

    private fun skipLogin()=launch{
        val skip by lazyDeferred {
            accountProvider.getLoginMode()
        }
        when(skip.await()){

            LoginMode.ASK_LOGIN -> Timber.i("skipLogin: Login Page")
            LoginMode.SKIP_LOGIN -> loadDiary()
        }
    }

    private fun configureNavigation() {
        navController = Navigation.findNavController(this,R.id.account_nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    private fun loadDiary(){
        startActivity(Intent(this,PersonalActivity::class.java))
        finish()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return fragmentInjector
    }

}
