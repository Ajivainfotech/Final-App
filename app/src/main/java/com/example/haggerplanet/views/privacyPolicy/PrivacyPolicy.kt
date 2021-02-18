package com.example.haggerplanet.views.privacyPolicy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentPrivacyPolicyBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class PrivacyPolicy:Fragment() {

    lateinit var privacyPolicyBinding: FragmentPrivacyPolicyBinding
    lateinit var privacyPolicyVM: PrivacyPolicyVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        privacyPolicyBinding= FragmentPrivacyPolicyBinding.inflate(LayoutInflater.from(context))
       return privacyPolicyBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        privacyPolicyVM=PrivacyPolicyVM(context!!,this)
        privacyPolicyBinding.privacyPolicyVM=privacyPolicyVM
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }
    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Privacy Policy"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }
}