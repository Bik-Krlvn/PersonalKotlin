package com.cheiseproj.bik_krl.personalkotlin.ui.auth.activity

import android.os.Bundle
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.base.BaseActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.fragment.LoginFragment

class AuthActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setLoginPage()
    }

    private fun setLoginPage() {
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_view,fragment).commit()
    }


}
