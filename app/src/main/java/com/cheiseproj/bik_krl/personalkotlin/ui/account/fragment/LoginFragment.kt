package com.cheiseproj.bik_krl.personalkotlin.ui.account.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.di.injector.Injectable
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity.PersonalActivity
import kotlinx.android.synthetic.main.fragment_login.view.*



class LoginFragment : BaseFragment(),Injectable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.login_button.setOnClickListener {context?.startActivity(Intent(context,PersonalActivity::class.java));activity?.finish() }
        return view
    }


}
