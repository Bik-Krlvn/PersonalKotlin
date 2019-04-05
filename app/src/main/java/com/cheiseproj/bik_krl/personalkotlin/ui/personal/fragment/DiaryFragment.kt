package com.cheiseproj.bik_krl.personalkotlin.ui.personal.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cheiseproj.bik_krl.personalkotlin.R
import com.cheiseproj.bik_krl.personalkotlin.di.injector.Injectable
import com.cheiseproj.bik_krl.personalkotlin.ui.base.BaseFragment
import com.cheiseproj.bik_krl.personalkotlin.ui.personal.activity.AddOrUpdateActivity
import com.cheiseproj.bik_krl.personalkotlin.viewmodel.DiaryViewModel
import kotlinx.android.synthetic.main.fragment_diary.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class DiaryFragment : BaseFragment(),Injectable {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DiaryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diary, container, false)
        view.fab_create.setOnClickListener { openAddOrUpdateActivity() }
        return view
    }

    private fun openAddOrUpdateActivity() {
        startActivity(Intent(context,AddOrUpdateActivity::class.java))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureViewModel()
    }

    private fun configureViewModel() {
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(DiaryViewModel::class.java)
    }

}
