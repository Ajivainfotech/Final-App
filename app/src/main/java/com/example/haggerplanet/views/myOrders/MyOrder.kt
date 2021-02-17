package com.example.haggerplanet.views.myOrders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentMyOrderBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class MyOrder:Fragment() {

    lateinit var myOrderVM: MyOrderVM
    lateinit var myOrderBinding: FragmentMyOrderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myOrderBinding= FragmentMyOrderBinding.inflate(LayoutInflater.from(context))
        return myOrderBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myOrderVM=MyOrderVM(context!!)
        myOrderBinding.myOrderVM=myOrderVM
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="My Orders"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
    }
}