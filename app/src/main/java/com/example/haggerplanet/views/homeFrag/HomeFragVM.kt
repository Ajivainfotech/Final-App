package com.example.haggerplanet.views.homeFrag

import android.content.Context
import android.os.Handler
import androidx.lifecycle.ViewModel
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.homeFrag.categoryAdapter.CategoryAdapter
import com.example.haggerplanet.views.homeFrag.moreProductAdapter.MoreProductAdapter
import com.example.haggerplanet.views.homeFrag.recomendantAdapter.RecomendAdapter
import com.example.haggerplanet.views.homeFrag.viewpageradapter.ViewPagerAdapter
import com.example.haggerplanet.retrofit.RetrofitService
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class HomeFragVM(val context: Context,val homeFrag: HomeFrag):ViewModel(), RetrofitResponse {

    var locale="en"

    var bannerList=ArrayList<BannerModel>()
    var catList=ArrayList<CategoryModel>()
    var additionalList=ArrayList<MoreProductModel>()
    var recommendList=ArrayList<RecommendantModel>()
    var categoryAdapter=CategoryAdapter(context)
    var moreProductAdapter=MoreProductAdapter(context)
    var recomendAdapter=RecomendAdapter(context)
    var viewPagerAdapter: ViewPagerAdapter? = null
    var timer: Timer? = null
    var userid=""
    var imageSlider=homeFrag.homeBinding.viewPager

    init {
        userid=PrefferenceFile.retrieveKey(context,CommonKeys.USER_ID)!!
        homeFrag.homeBinding.viewPager.isFocusable = true
        viewPagerAdapter = ViewPagerAdapter(context)
        imageSlider.adapter = viewPagerAdapter
        homeFrag.homeBinding.indicator.setViewPager(homeFrag.homeBinding.viewPager)
        getHomeData()
       getMoreProductData()
        getRecomendData()
    }

    fun setupAutoPager() {
        var currentPage = 0
        Log.e("SlideTimer", "Exe")
        val handler = Handler()

        val update = Runnable {
            imageSlider.setCurrentItem(currentPage, true)
            if (currentPage == bannerList.size) {
                currentPage = 0
            } else {
                ++currentPage
            }
        }

        timer = Timer()

        timer!!.schedule(object : TimerTask() {

            override fun run() {
                handler.post(update)
            }
        }, 1000, 3000)


    }

    fun getHomeData(){
        try {

            var jsonObj=JSONObject()
            jsonObj.put("ln",locale)
            RetrofitService(context,this,WebApiKeys.GET_HOME_DATA,WebApiKeys.GET_HOME_DATA_REQ,3,jsonObj).callService(true,"")
        }catch (e:Exception){

            e.printStackTrace()
        }

    }

    fun getMoreProductData(){
        try {

            var jsonObj=JSONObject()
            jsonObj.put("ln",locale)
          //  jsonObj.put("user_id",2)
            RetrofitService(context,this,WebApiKeys.MORE_PRODUCT,WebApiKeys.MORE_PRODUCT_REQ,3,jsonObj).callService(true,"")
        }catch (e:Exception){

            e.printStackTrace()
        }

    }

    fun getRecomendData(){
        try {

            var jsonObj=JSONObject()
            jsonObj.put("ln",locale)
              jsonObj.put("user_id",userid)
            RetrofitService(context,this,WebApiKeys.RECOMMENDANT,WebApiKeys.RECOMMENDANT_REQ,3,jsonObj).callService(true,"")
        }catch (e:Exception){

            e.printStackTrace()
        }

    }


    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {

        when(requestCode){

            WebApiKeys.GET_HOME_DATA_REQ->{

                try {

                    var jsonObject=JSONObject(response)

                    if (jsonObject.getBoolean("status")){

                        var data=jsonObject.getJSONObject("responseData")

                        if (!data.isNull("banner")){

                            var bannerArray=data.getJSONArray("banner")
                            if (bannerArray.length()>0){

                                for (i in 0 until bannerArray.length()){
                                    var bannerImages=bannerArray.getJSONObject(i)
                                    var bannerModel=BannerModel()
                                    bannerModel.id=bannerImages.getString("id")
                                    bannerModel.tittle=bannerImages.getString("title")
                                    bannerModel.image=WebApiKeys.IMAGE_URL+bannerImages.getString("image")
                                    bannerList.add(bannerModel)
                                }

                                viewPagerAdapter!!.addDataInList(bannerList)
                                homeFrag.homeBinding.indicator.setViewPager(homeFrag.homeBinding.viewPager)

                            }
                        }
                        if (!data.isNull("categories")){

                            var catArray=data.getJSONArray("categories")
                            if (catArray.length()>0){
                                for (j in 0 until catArray.length()){
                                    var catData=catArray.getJSONObject(j)
                                    var categoryModel=CategoryModel()
                                    categoryModel.id=catData.getString("id")
                                    categoryModel.tittle=catData.getString("name")
                                    categoryModel.image=WebApiKeys.IMAGE_URL+catData.getString("image")
                                    catList.add(categoryModel)
                                }
                                categoryAdapter.addDatainList(catList)
                            }
                        }
                    }
                }catch (e:Exception){

                    e.printStackTrace()
                }
            }
            WebApiKeys.MORE_PRODUCT_REQ->{

                try {

                    var jsonObject=JSONObject(response)
                    if (jsonObject.getBoolean("status")){

                        Log.e("moreproduct", "data$jsonObject")
                        var data=jsonObject.getJSONObject("responseData")

                        if (!data.isNull("additional_products")){

                            var additionalProductArray=data.getJSONArray("additional_products")
                            if (additionalProductArray.length()>0){

                                for (i in 0 until additionalProductArray.length()){
                                    var additionalProducts=additionalProductArray.getJSONObject(i)
                                    var moreProductModel=MoreProductModel()
                                    moreProductModel.id=additionalProducts.getString("id")
                                    moreProductModel.tittle=additionalProducts.getString("title")
                                    moreProductModel.price=additionalProducts.getString("sale_price")
                                    moreProductModel.image=WebApiKeys.IMAGE_URL+additionalProducts.getString("image")
                                    additionalList.add(moreProductModel)
                                }

                                moreProductAdapter!!.addDatainList(additionalList)

                            }
                        }
                    }

                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
            WebApiKeys.RECOMMENDANT_REQ->{

                try {
                    var jsonObject=JSONObject(response)

                    if (jsonObject.getBoolean("status")){

                        Log.e("RecomendData", "Ress$jsonObject")
                        Log.e("moreproduct", "data$jsonObject")
                        var data=jsonObject.getJSONObject("responseData")

                        if (!data.isNull("recommend_products")){

                            var recommendProductArray=data.getJSONArray("recommend_products")
                            if (recommendProductArray.length()>0){

                                for (i in 0 until recommendProductArray.length()){
                                    var additionalProducts=recommendProductArray.getJSONObject(i)
                                    var recommandModel=RecommendantModel()
                                    recommandModel.id=additionalProducts.getString("id")
                                    recommandModel.tittle=additionalProducts.getString("title")
                                   recommandModel.price=additionalProducts.getString("sale_price")
                                    recommandModel.image=WebApiKeys.IMAGE_URL+additionalProducts.getString("image")
                                    recommendList.add(recommandModel)
                                }

                                recomendAdapter!!.addDatainList(recommendList)

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

    data class BannerModel(
            var image:String="",
            var id:String="",
            var tittle:String=""
    )
    data class CategoryModel(
        var image:String="",
        var id:String="",
        var tittle:String=""
    )

    data class RecommendantModel(
            var image:String="",
            var id:String="",
            var price:String="",
            var tittle:String=""
    )

    data class MoreProductModel(
            var image:String="",
            var id:String="",
            var tittle:String="",
            var price:String=""
    )
}