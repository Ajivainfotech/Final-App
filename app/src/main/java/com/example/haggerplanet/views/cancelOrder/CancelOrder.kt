package com.example.haggerplanet.views.cancelOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.CancelOrderBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class CancelOrder:Fragment() {
    lateinit var cancelOrderVM: CancelOrderVM
    lateinit var cancelOrderBinding: CancelOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cancelOrderBinding= CancelOrderBinding.inflate(LayoutInflater.from(context!!))
        return cancelOrderBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelOrderVM= CancelOrderVM(context!!)
        cancelOrderBinding.cancelOrderVM=cancelOrderVM
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Cancel Order"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }
}