package com.example.haggerplanet.views.privacyPolicy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicy:Fragment() {

    lateinit var privacyPolicyBinding: FragmentPrivacyPolicyBinding
    lateinit var privacyPolicyVM: PrivacyPolicyVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        privacyPolicyBinding= FragmentPrivacyPolicyBinding.inflate(LayoutInflater.from(context))
       return privacyPolicyBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        privacyPolicyVM=PrivacyPolicyVM(context!!)
        privacyPolicyBinding.privacyPolicyVM=privacyPolicyVM
    }
}