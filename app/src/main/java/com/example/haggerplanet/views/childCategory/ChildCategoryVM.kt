package com.example.haggerplanet.views.childCategory

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.category.CategoryVM
import com.example.haggerplanet.views.childCategory.childCategoryAdapter.ChildCategoryAdapter
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*
import org.json.JSONObject

class ChildCategoryVM(val context: Context,val childCategory: ChildCategory):ViewModel() ,RetrofitResponse{

    var childCategoryAdapter=ChildCategoryAdapter(context)

    var categoryList=ArrayList<CategoryVM.CategoryModel>()
    var id=""
    var locale="en"
    var isListEmpty=ObservableBoolean(false)


    init {
        getBundle()
    }
    fun getBundle(){
        if (childCategory.arguments!=null){
            var data=childCategory.arguments
            if (data!!.containsKey(CommonKeys.CATEGORY_ID)){
                id=data.getString(CommonKeys.CATEGORY_ID)!!
                Home.mainBinding.itemLayout.toolTitle.text=data.getString(CommonKeys.CATEGORY_NAME)
                Log.e("ChildCategory", "======$id")
                getMainCategory()
            }
        }
    }


    fun getMainCategory(){

        try {

            var jsonObj= JSONObject()
            jsonObj.put("ln",locale)
            jsonObj.put("category_sub_group_id",id)
            RetrofitService(context,this, WebApiKeys.CHILD_CATEGORY, WebApiKeys.CHILD_CATEGORY_REQ,3,jsonObj).callService(true,"")
        }catch (e:Exception){

            e.printStackTrace()
        }
    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {

        when(requestCode){

            WebApiKeys.CHILD_CATEGORY_REQ->{

                try {
                    var jsonObject= JSONObject(response)
                    if (jsonObject.getBoolean("status")){

                        Log.e("ChildCategory", "Res$jsonObject")
                        if (!jsonObject.isNull("responseData")){

                            var resData=jsonObject.getJSONObject("responseData")
                            if(!resData.isNull("childcategories")){

                                var categoryArray=resData.getJSONArray("childcategories")
                                if (categoryArray.length()>0){
                                    categoryList.clear()
                                    for (i in 0 until categoryArray.length()){
                                        var categorydata=categoryArray.getJSONObject(i)
                                        var categoryModel= CategoryVM.CategoryModel()
                                        categoryModel.id=categorydata.getString("id")
                                        categoryModel.description=categorydata.getString("description")
                                        categoryModel.name=categorydata.getString("name")
                                        categoryModel.image= WebApiKeys.IMAGE_URL+categorydata.getString("image")
                                        categoryList.add(categoryModel)
                                    }
                                    childCategoryAdapter.adddataInList(categoryList)
                                }else{
                                    isListEmpty.set(true)
                                }
                            }
                        }
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }

    }

    override fun onError(requestCode: Int, msg: String) {

    }

}