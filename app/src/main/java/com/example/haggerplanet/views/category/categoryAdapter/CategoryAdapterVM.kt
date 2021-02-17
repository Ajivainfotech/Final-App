package com.example.haggerplanet.views.category.categoryAdapter

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.views.category.CategoryVM
import com.example.haggerplanet.views.childCategory.ChildCategory
import com.example.haggerplanet.utils.MethodsUtil

class CategoryAdapterVM(val context: Context, var categoryModel: CategoryVM.CategoryModel, var position: Int):ViewModel() {

    fun onClickItem(){
        var bundle= Bundle()
        bundle.putString(CommonKeys.CATEGORY_ID,categoryModel.id)
        bundle.putString(CommonKeys.CATEGORY_NAME,categoryModel.name)
        var category= ChildCategory()
        category.arguments=bundle
        MethodsUtil.addFragment(context,category)
    }
}