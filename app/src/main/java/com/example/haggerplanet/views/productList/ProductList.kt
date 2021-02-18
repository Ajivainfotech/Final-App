package com.example.haggerplanet.views.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.ProductListBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class ProductList:Fragment() {

    lateinit var productListVM: ProductListVM
    lateinit var productListBinding: ProductListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        productListBinding= ProductListBinding.inflate(LayoutInflater.from(context))
        return productListBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListVM= ProductListVM(context!!,this)
        productListBinding.productListVM=productListVM
    }

    override fun onResume() {
        super.onResume()
        setToolbar()

    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }
}