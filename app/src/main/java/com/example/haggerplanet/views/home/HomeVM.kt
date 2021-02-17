package com.example.haggerplanet.views.home

import android.content.Context
import androidx.core.view.GravityCompat
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.R
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.aboutUs.AboutUs
import com.example.haggerplanet.views.cart.Cart
import com.example.haggerplanet.views.homeFrag.HomeFrag
import com.example.haggerplanet.views.homeFrag.HomeFragVM
import com.example.haggerplanet.views.homeFrag.drawerCategory.DrawerCategoryAdapter
import com.example.haggerplanet.views.notification.Notifications
import com.example.haggerplanet.views.privacyPolicy.PrivacyPolicy
import com.example.haggerplanet.views.profile.Profile
import com.example.haggerplanet.views.sellFragment.SellFrag
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.haggerplanet.utils.MethodsUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.json.JSONObject

class HomeVM(val context: Context,val home: Home):ViewModel(),RetrofitResponse {
    var catList=ArrayList<HomeFragVM.CategoryModel>()

    var locale="en"
    var name=ObservableField("")
    var image=ObservableField("")
    var categoryAdapter= DrawerCategoryAdapter(context)
    init {
        var name=PrefferenceFile.retrieveKey(context,CommonKeys.USER_NAME)
        //home.itemLayout.navProfileImg
        Home.mainBinding.headerView.txtNavUsername.text=name
        openFrag(context,HomeFrag())
        getHomeData()
        onClickMennu()
        onClickSearch()
        onClickBack()
    }
    fun openFrag(context: Context, fragment: Fragment) {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.frameHome, fragment)
                .commitAllowingStateLoss()
    }

    fun onClickBack(){
        Home.mainBinding.itemLayout.ivBack.setOnClickListener {
            home.onBackPressed()
        }
    }

    fun onClickMennu(){
        Home.mainBinding.itemLayout.ivMenu.setOnClickListener {
            Home.mainBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun onClickSearch(){
        Home.mainBinding.itemLayout.ivNotification.setOnClickListener {
            MethodsUtil.loadFragment(context, Notifications())
        }
    }

    fun onClickAboutUs(){
        closeDrawer()
        MethodsUtil.loadFragment(context,AboutUs())
    }
    fun onClickPrivacyPoliocy(){
        closeDrawer()
        MethodsUtil.loadFragment(context,PrivacyPolicy())
    }
    fun onClickLogout(){
        PrefferenceFile.logout(context)
    }

    fun closeDrawer(){

        Home.mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    var navigationClick =
            BottomNavigationView.OnNavigationItemSelectedListener { p0 ->
                when (p0.itemId) {
                    R.id.nav_bottom_home -> {
                        openFrag(context,HomeFrag())
                    }
                    R.id.nav_sell_cart -> {
                        MethodsUtil.loadFragment(context,SellFrag() )
                    }
                    R.id.nav_bottom_cart -> {
                        MethodsUtil.loadFragment(context, Cart())
                    }
                    R.id.nav_bottom_profile -> {
                        MethodsUtil.loadFragment(context, Profile())
                    }
                }
                true
            }


    fun getHomeData(){
        try {

            var jsonObj= JSONObject()
            jsonObj.put("ln",locale)
            RetrofitService(context,this, WebApiKeys.GET_HOME_DATA, WebApiKeys.GET_HOME_DATA_REQ,3,jsonObj).callService(true,"")
        }catch (e:Exception){

            e.printStackTrace()
        }

    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {

        when(requestCode){

            WebApiKeys.GET_HOME_DATA_REQ->{

                try {

                    var jsonObject=JSONObject(response)

                    if (jsonObject.getBoolean("status")) {

                        var data = jsonObject.getJSONObject("responseData")

                        if (!data.isNull("categories")) {

                            var catArray=data.getJSONArray("categories")
                            if (catArray.length()>0){
                                for (j in 0 until catArray.length()){
                                    var catData=catArray.getJSONObject(j)
                                    var categoryModel= HomeFragVM.CategoryModel()
                                    categoryModel.id=catData.getString("id")
                                    categoryModel.tittle=catData.getString("name")
                                    categoryModel.image=WebApiKeys.IMAGE_URL+catData.getString("image")
                                    catList.add(categoryModel)
                                }
                                categoryAdapter.addDatainList(catList)
                            }
                        }
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onError(requestCode: Int, msg: String) {
    }

}