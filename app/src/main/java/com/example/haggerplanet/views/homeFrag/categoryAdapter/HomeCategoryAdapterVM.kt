package com.example.haggerplanet.views.homeFrag.categoryAdapter

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.views.category.Category
import com.example.haggerplanet.views.homeFrag.HomeFragVM
import com.example.haggerplanet.utils.MethodsUtil

class HomeCategoryAdapterVM(
    val context: Context,
    var model: HomeFragVM.CategoryModel,
    var position: Int
):ViewModel() {

    fun onClickItem(){
        var bundle= Bundle()
        bundle.putString(CommonKeys.CATEGORY_ID,model.id)
        bundle.putString(CommonKeys.CATEGORY_NAME,model.tittle)
        var category= Category()
        category.arguments=bundle
        MethodsUtil.addFragment(context,category)
    }
}