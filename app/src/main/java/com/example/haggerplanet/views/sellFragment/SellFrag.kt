package com.example.haggerplanet.views.sellFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentSellBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

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

    override fun onResume() {
        super.onResume()
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.toolTitle.text=""
        Home.mainBinding.itemLayout.ivSearch.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.ivBack.visibility=View.GONE
    }
}