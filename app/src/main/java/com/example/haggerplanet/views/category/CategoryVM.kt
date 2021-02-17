package com.example.haggerplanet.views.category

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.category.categoryAdapter.CategoryAdapter
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*
import org.json.JSONObject

class CategoryVM(val context: Context,val category: Category):ViewModel(),RetrofitResponse {

    var locale="en"
    var id=""
    var categoryAdapter= CategoryAdapter(context)
    var categoryList=ArrayList<CategoryModel>()
    var isListEmpty= ObservableBoolean(false)

    init {
        getBundle()
    }
    fun getBundle(){
        if (category.arguments!=null){
            var data=category.arguments
            if (data!!.containsKey(CommonKeys.CATEGORY_ID)){
                id=data.getString(CommonKeys.CATEGORY_ID)!!
                Home.mainBinding.itemLayout.toolTitle.text=data.getString(CommonKeys.CATEGORY_NAME)
                getMainCategory()
            }
        }
    }


    fun getMainCategory(){

        try {

            var jsonObj= JSONObject()
            jsonObj.put("ln",locale)
            jsonObj.put("category_group_id",id)
            RetrofitService(context,this, WebApiKeys.SUBCATEGORY, WebApiKeys.SUBCATEGORY_REQ,3,jsonObj).callService(true,"")
        }catch (e:Exception){

            e.printStackTrace()
        }
    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {

        when(requestCode){

            WebApiKeys.SUBCATEGORY_REQ->{

                try {
                    var jsonObject= JSONObject(response)
                    if (jsonObject.getBoolean("status")){

                        Log.e("SubCategory", "Res$jsonObject")
                        if (!jsonObject.isNull("responseData")){

                            var resData=jsonObject.getJSONObject("responseData")
                            if(!resData.isNull("subcategories")){

                                var categoryArray=resData.getJSONArray("subcategories")
                                if (categoryArray.length()>0){
                                    for (i in 0 until categoryArray.length()){
                                        var categorydata=categoryArray.getJSONObject(i)
                                        var categoryModel=CategoryModel()
                                        categoryModel.id=categorydata.getString("id")
                                        categoryModel.description=categorydata.getString("description")
                                        categoryModel.name=categorydata.getString("name")
                                        categoryModel.image= WebApiKeys.IMAGE_URL+categorydata.getString("image")
                                        categoryList.add(categoryModel)
                                    }
                                    categoryAdapter.adddataInList(categoryList)
                                }
                            }
                            else{
                                isListEmpty.set(true)
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

    data class CategoryModel(
            var id:String="",
            var name:String="",
            var description:String="",
            var price:String="",
            var image:String=""
    )
}