package com.example.haggerplanet.views.home

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.LanguageAlertBinding
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.aboutUs.AboutUs
import com.example.haggerplanet.views.cart.Cart
import com.example.haggerplanet.views.editprofile.EditProfile
import com.example.haggerplanet.views.homeFrag.HomeFrag
import com.example.haggerplanet.views.homeFrag.HomeFragVM
import com.example.haggerplanet.views.homeFrag.drawerCategory.DrawerCategoryAdapter
import com.example.haggerplanet.views.notification.Notifications
import com.example.haggerplanet.views.privacyPolicy.PrivacyPolicy
import com.example.haggerplanet.views.profile.Profile
import com.example.haggerplanet.views.sellFragment.SellFrag
import com.example.haggerplanet.views.termsAndConditions.TermsAndConditions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.nav_header.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.json.JSONObject


class HomeVM(val context: Context,val home: Home):ViewModel(),RetrofitResponse {
    var catList=ArrayList<HomeFragVM.CategoryModel>()

    lateinit var languageAlertBinding: LanguageAlertBinding
    lateinit var languageAlert:AlertDialog

    var locale="en"
    var name=ObservableField("")
    var image=ObservableField("")
    var categoryAdapter= DrawerCategoryAdapter(context)
    init {
        var name=PrefferenceFile.retrieveKey(context,CommonKeys.USER_NAME)
        Home.mainBinding.headerView.txtNavUsername.text=name
        openFrag(context,HomeFrag())
        getHomeData()
        onClickMennu()
        onClickSearch()
        onClickBack()
        onClickLang()
        onClickImage()

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


    fun onClickLang(){
        Home.mainBinding.itemLayout.tvLang.setOnClickListener {
            onClickMenuButton(Home.mainBinding.itemLayout.tvLang)

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

    fun onClickImage(){
        Home.mainBinding.headerView.navProfileImg.setOnClickListener {
            closeDrawer()
            MethodsUtil.loadFragment(context,EditProfile())
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
    fun onClickTermsAndConditions(){
        closeDrawer()
        MethodsUtil.loadFragment(context,TermsAndConditions())
    }

    fun onClickLogout(){
        PrefferenceFile.logout(context)
    }

    fun closeDrawer(){

        Home.mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    fun openLanguageAlert() {

      /*  try {

                var docBuilder = AlertDialog.Builder(context)
                languageAlertBinding =
                        DataBindingUtil.inflate(
                                LayoutInflater.from(context),
                                R.layout.language_alert,
                                null,
                                false
                        )

                docBuilder.setView(languageAlertBinding!!.root)
                languageAlert = docBuilder.create()
                languageAlert.window!!.attributes.windowAnimations = R.style.DialogAnimation
                langVM()
                languageAlert.show()
                languageAlert.window!!.setGravity(Gravity.CENTER)
                languageAlert.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        } catch (e: Exception) {
            e.printStackTrace()
        }*/
    }

    fun onClickMenuButton(view: View) {

        try {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(R.menu.toolbar_menu, popupMenu.menu)

      /*      val m = popupMenu.menu

            if (!Chat.reportVisible.get()!!) {

                m.removeItem(R.id.report)

            } else {

            }*/

            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.English -> {

                       // FragmentLoadActivity.menuChatInterface!!.onCLickOptions(CommonKeys.GROUP_INFO)

                        true
                    }
                    R.id.Francais -> {

                      //  FragmentLoadActivity.menuChatInterface!!.onCLickOptions(CommonKeys.CHAT_SEARCH)
                        true
                    }
                    R.id.中国人 -> {

                      //  FragmentLoadActivity.menuChatInterface!!.onCLickOptions(CommonKeys.CLEAR_CHAT)
                        true

                    }
                    R.id.Deutsch -> {

                    // FragmentLoadActivity.menuChatInterface!!.onCLickOptions(CommonKeys.EXIT_GROUP)

                        true

                    }
                    R.id.Turk -> {
                     //   FragmentLoadActivity.menuChatInterface!!.onCLickOptions(CommonKeys.REPORT)

                        true

                    }
                    R.id.abbr -> {
                        //   FragmentLoadActivity.menuChatInterface!!.onCLickOptions(CommonKeys.REPORT)

                        true

                    }
                    R.id.Espanola -> {
                        //   FragmentLoadActivity.menuChatInterface!!.onCLickOptions(CommonKeys.REPORT)

                        true

                    }
                    else -> {
                        false
                    }
                }
            }
            popupMenu.show()

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    private fun langVM() {

        var forgotPasswordAlert =
                LanguageAlertVM(context, languageAlert)
        languageAlertBinding!!.languageAlertVM = forgotPasswordAlert
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