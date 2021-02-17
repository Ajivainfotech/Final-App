package com.example.haggerplanet.views.homeFrag.drawerCategory

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.category.Category
import com.example.haggerplanet.views.home.Home
import com.example.haggerplanet.views.homeFrag.HomeFragVM

class DrawerCategoryAdapterVM(val context: Context, val model: HomeFragVM.CategoryModel, position: Int):ViewModel() {

    fun onClickItem(){
        Home.homeVM.closeDrawer()
        var bundle= Bundle()
        bundle.putString(CommonKeys.CATEGORY_ID,model.id)
        bundle.putString(CommonKeys.CATEGORY_NAME,model.tittle)
        var category= Category()
        category.arguments=bundle
        MethodsUtil.addFragment(context,category)
    }
}