package com.example.haggerplanet.views.aboutUs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentAboutUsBinding

class AboutUs:Fragment() {

    lateinit var aboutUsVM: AboutUsVM
    lateinit var aboutUsBinding: FragmentAboutUsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        aboutUsBinding= FragmentAboutUsBinding.inflate(LayoutInflater.from(context))
        return aboutUsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutUsVM=AboutUsVM(context!!)
        aboutUsBinding.aboutUsVM=aboutUsVM
    }
}