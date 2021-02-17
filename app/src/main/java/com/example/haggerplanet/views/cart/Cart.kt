package com.example.haggerplanet.views.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.CartDesignBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*


class Cart:Fragment() {

    lateinit var cartBinding: CartDesignBinding
    lateinit var cartVM: CartVM
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        cartBinding= CartDesignBinding.inflate(LayoutInflater.from(context))
       return cartBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartVM= CartVM(context!!)
        cartBinding.cartVM=cartVM
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text=""
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
    }
}