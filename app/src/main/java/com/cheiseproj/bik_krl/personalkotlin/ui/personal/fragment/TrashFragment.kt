package com.cheiseproj.bik_krl.personalkotlin.ui.personal.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cheiseproj.bik_krl.personalkotlin.R

/**
 * A simple [Fragment] subclass.
 *
 */
class TrashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trash, container, false)
    }


}
