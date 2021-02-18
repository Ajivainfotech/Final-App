package com.example.haggerplanet.views.contactUs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentContactusBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class ConactUs:Fragment() {
    lateinit var fragmentContactusBinding:FragmentContactusBinding
    lateinit var contactUsVM: ContactUsVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentContactusBinding= FragmentContactusBinding.inflate(LayoutInflater.from(context))
       return fragmentContactusBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactUsVM=ContactUsVM(context!!)
        fragmentContactusBinding.contactUsVM=contactUsVM
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }
    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Contact Us"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }
}