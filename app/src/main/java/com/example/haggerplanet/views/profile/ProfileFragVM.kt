package com.example.haggerplanet.views.profile

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.views.coupon.Coupon
import com.example.haggerplanet.views.helpCenter.HelpCenter
import com.example.haggerplanet.views.messenger.Messengers
import com.example.haggerplanet.views.myOrders.MyOrder
import com.example.haggerplanet.views.profileDetails.ProfileDetails
import com.example.haggerplanet.views.setting.Setting
import com.example.haggerplanet.views.wishList.WishList
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.views.contactUs.ConactUs
import com.example.haggerplanet.views.editprofile.EditProfile
import com.example.haggerplanet.views.savecards.SaveCard

class ProfileFragVM(val context: Context):ViewModel() {

    var name=ObservableField("")
    var image=ObservableField("")


    init {
        name.set(PrefferenceFile.retrieveKey(context,CommonKeys.USER_NAME))
    }

    fun onClickMyOrders(){

        MethodsUtil.addFragment(context,MyOrder())
    }

    fun onClickMessenger(){

        MethodsUtil.addFragment(context,Messengers())
    }

    fun onClickWishList(){

        MethodsUtil.addFragment(context,WishList())
    }
    fun onClickCoupon(){
        MethodsUtil.addFragment(context, EditProfile())

    }

    fun onClickSaveCards(){
        MethodsUtil.addFragment(context,SaveCard())


    }
    fun onClickCoupons(){
        MethodsUtil.addFragment(context,Coupon())

    }

    fun onClickContactUs(){
        MethodsUtil.addFragment(context,ConactUs())
    }

    fun onClickSetting(){
        MethodsUtil.addFragment(context,Setting())
    }

    fun onClickHelpcenter(){
        MethodsUtil.addFragment(context,HelpCenter())

    }
}