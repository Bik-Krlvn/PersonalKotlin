package com.cheiseproj.bik_krl.personalkotlin.ui.auth.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.base.BaseFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.main.activity.diary.DiaryActivity
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.viewmodel.AuthViewModel
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.AuthStatus
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.InputValidationProvider
import kotlinx.android.synthetic.main.fragment_login.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
import javax.inject.Inject


class LoginFragment : BaseFragment() {
    private lateinit var viewModel: AuthViewModel
    @Inject lateinit var inputValidationProvider: InputValidationProvider
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            btn_login.setOnClickListener { authenticateUser() }
        }
    }

    private fun authenticateUser() {
        if (!inputValidationProvider.isEditTextFilled(view?.et_email!!,"invalid email",true)){
            return
        }
        if (!inputValidationProvider.isEditTextFilled(view?.et_password!!,"invalid password")){
            return
        }
        val email = view?.et_email?.text.toString()
        val password = view?.et_password?.text.toString()
        viewModel.authenticateWithEmail(email,password)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureViewModel()
    }

    private fun configureViewModel() {
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(AuthViewModel::class.java)
        viewModel.authUserData().removeObservers(viewLifecycleOwner)
        viewModel.authUserData().observe(viewLifecycleOwner, Observer {
            it?.let {auth ->
                when(auth.status){
                    AuthStatus.LOADING -> Unit
                    AuthStatus.AUTHENTICATED -> {
                        Timber.i("welcome user: ${auth.data?.id}")
                        activity?.startActivity<DiaryActivity>()
                        activity?.finish()
                    }
                    AuthStatus.ERROR -> toast("${auth.message}")
                    AuthStatus.LOGOUT -> Unit
                }
            }
        })

    }

}
