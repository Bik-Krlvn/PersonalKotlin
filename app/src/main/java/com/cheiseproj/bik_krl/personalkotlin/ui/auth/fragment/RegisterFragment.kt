package com.cheiseproj.bik_krl.personalkotlin.ui.auth.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.data.db.entity.UserEntity
import com.cheiseproj.bik_krl.personalkotlin.base.BaseFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.auth.viewmodel.AuthViewModel
import com.cheiseproj.bik_krl.personalkotlin.utils.provider.InputValidationProvider
import kotlinx.android.synthetic.main.fragment_register.view.*
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
import javax.inject.Inject

class RegisterFragment : BaseFragment() {
    @Inject lateinit var viewModel: AuthViewModel
    @Inject lateinit var inputValidationProvider: InputValidationProvider
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            btn_create.setOnClickListener { createAccount() }
            ed_email.addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    if (s!!.isNotEmpty()){
                        viewModel.checkEmail(s.toString())
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureViewModel()
    }

    private fun configureViewModel() {
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(AuthViewModel::class.java)
        viewModel.errorAccount.removeObservers(viewLifecycleOwner)
        viewModel.errorAccount.observe(viewLifecycleOwner, Observer {
           it?.let {error -> showErrorMessage(error) }
        })
        viewModel.userId.observe(this, Observer {
            Timber.i("userId: $it")
        })
    }

    private fun showErrorMessage(@StringRes error: Int) {
        view?.ed_email?.error = getString(error)
        toast(error)
    }

    private fun createAccount() {
        if (!inputValidationProvider.isEditTextFilled(view?.ed_username!!,"invalid username")) return
        if (!inputValidationProvider.isEditTextFilled(view?.ed_email!!,"invalid email",true)) return
        if (!inputValidationProvider.isEditTextFilled(view?.ed_password!!,"invalid password")) return
        if (!inputValidationProvider.isEditTextFilledMatches
                    (view?.ed_password!!,view?.ed_confirm_password!!)) return

        val username = view?.ed_username?.text.toString()
        val email = view?.ed_email?.text.toString()
        val password = view?.ed_password?.text.toString()
        viewModel.addNewUser(UserEntity(username,email,password))
    }
}
