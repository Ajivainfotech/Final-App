package com.example.haggerplanet.views.productList.productListAdapter

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.views.category.CategoryVM
import com.example.haggerplanet.views.productDetails.ProductDetails
import com.example.haggerplanet.utils.MethodsUtil

class ProductListAdpterVM(val context: Context, val model: CategoryVM.CategoryModel):ViewModel() {

    fun onClickItem(){
        var bundle= Bundle()
        bundle.putString(CommonKeys.CATEGORY_ID,model.id)
        bundle.putString(CommonKeys.CATEGORY_NAME,model.name)
        var category= ProductDetails()
        category.arguments=bundle
        MethodsUtil.addFragment(context,category)
    }
}