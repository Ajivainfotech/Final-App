package com.example.haggerplanet.views.productDetails

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.cart.Cart
import com.example.haggerplanet.views.relatedProducts.RelatedProductsAdapter

class ProductDetailsVM(val context: Context):ViewModel() {

    var relatedProductsAdapter= RelatedProductsAdapter(context)

    fun onClickAddTocart(){
        MethodsUtil.loadFragment(context,Cart())
    }
}