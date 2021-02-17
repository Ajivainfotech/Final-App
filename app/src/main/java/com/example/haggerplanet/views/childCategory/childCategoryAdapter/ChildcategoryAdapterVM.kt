package com.example.haggerplanet.views.childCategory.childCategoryAdapter

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.views.category.CategoryVM
import com.example.haggerplanet.views.productList.ProductList
import com.example.haggerplanet.utils.MethodsUtil

class ChildcategoryAdapterVM(val context: Context,var  categoryModel: CategoryVM.CategoryModel, position: Int):ViewModel() {


    fun onClickItem(){
        var bundle= Bundle()
        bundle.putString(CommonKeys.CATEGORY_ID,categoryModel.id)
        bundle.putString(CommonKeys.CATEGORY_NAME,categoryModel.name)
        var category= ProductList()
        category.arguments=bundle
        MethodsUtil.addFragment(context,category)
    }
}