package com.cheiseproj.bik_krl.personalkotlin.ui.account.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.di.injector.Injectable
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseFragment

class RegisterFragment : BaseFragment(),Injectable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


}
