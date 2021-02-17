package com.example.haggerplanet.views.sellFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentSellBinding

class SellFrag:Fragment() {

    lateinit var sellBinding: FragmentSellBinding
    lateinit var sellFragVM: SellFragVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sellBinding= FragmentSellBinding.inflate(LayoutInflater.from(context))
        return sellBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sellFragVM=SellFragVM(context!!)
        sellBinding.sellFragVM=sellFragVM
    }
}