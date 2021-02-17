package com.example.haggerplanet.views.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.ProductDetailsBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class ProductDetails:Fragment() {
    lateinit var productDetailsVM: ProductDetailsVM
    lateinit var productDetailsBinding: ProductDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        productDetailsBinding= ProductDetailsBinding.inflate(LayoutInflater.from(context))
        return productDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productDetailsVM=ProductDetailsVM(context!!)
        productDetailsBinding.productDetailsVM=productDetailsVM
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