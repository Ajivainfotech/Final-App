package com.example.haggerplanet.views.productList

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
import com.example.haggerplanet.views.productList.productListAdapter.ProductListAdapter
import kotlinx.android.synthetic.main.toolbar.view.*
import org.json.JSONObject

class ProductListVM(val context: Context,val productList: ProductList):ViewModel() ,RetrofitResponse{

    var childCategoryAdapter= ProductListAdapter(context)

    var categoryList=ArrayList<CategoryVM.CategoryModel>()
    var id=""
    var locale="en"
    var isListEmpty= ObservableBoolean(false)


    init {
        getBundle()
    }
    fun getBundle(){
        if (productList.arguments!=null){
            var data=productList.arguments
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
            jsonObj.put("category_id",id)
            RetrofitService(context,this, WebApiKeys.CATEGORY_PRODUCTS, WebApiKeys.CATEGORY_PRODUCTS_REQ,3,jsonObj).callService(true,"")
        }catch (e:Exception){

            e.printStackTrace()
        }
    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {

        when(requestCode){

            WebApiKeys.CATEGORY_PRODUCTS_REQ->{

                try {
                    var jsonObject= JSONObject(response)
                    if (jsonObject.getBoolean("status")){

                        Log.e("ProductList", "Res$jsonObject")
                        if (!jsonObject.isNull("responseData")){

                            var resData=jsonObject.getJSONObject("responseData")
                            if(!resData.isNull("products")){

                                var categoryArray=resData.getJSONArray("products")
                                if (categoryArray.length()==0){
                                    isListEmpty.set(true)
                                }
                                if (categoryArray.length()>0){
                                    categoryList.clear()
                                    for (i in 0 until categoryArray.length()){
                                        var categorydata=categoryArray.getJSONObject(i)
                                        var categoryModel= CategoryVM.CategoryModel()
                                        categoryModel.id=categorydata.getString("product_id")
                                        categoryModel.description=categorydata.getString("description")
                                        categoryModel.name=categorydata.getString("title")
                                        categoryModel.price="$"+categorydata.getString("sale_price")
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