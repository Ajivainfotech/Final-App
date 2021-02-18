package com.example.haggerplanet.views.wishList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentWhislistBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class WishList:Fragment() {

    lateinit var wishListVM: WishListVM
    lateinit var whislistBinding: FragmentWhislistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        whislistBinding= FragmentWhislistBinding.inflate(LayoutInflater.from(context))
        return whislistBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishListVM=WishListVM(context!!)
        whislistBinding.wishListVM=wishListVM
        setToolbar()
    }


    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="WishList"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }
}