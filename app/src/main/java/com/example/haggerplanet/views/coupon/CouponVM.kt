package com.example.haggerplanet.views.coupon

import android.content.Context
import androidx.lifecycle.ViewModel
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.utils.WebApiKeys
import org.json.JSONObject

class CouponVM(val context: Context):ViewModel() ,RetrofitResponse{
    var couponAdapter=CouponAdapter(context)

    var lagid="en"
    init {
        getCoupons()
    }
    fun getCoupons(){

        try {
            var id= PrefferenceFile.retrieveKey(context, CommonKeys.USER_ID)

            val  jsonObject = JSONObject()
            jsonObject.put("user_id",1)
            jsonObject.put("ln",lagid)
            RetrofitService(context,this, WebApiKeys.COUPONS, WebApiKeys.COUPONS_REQ,3,jsonObject)
                    .callService(true,"")
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {

        when(requestCode){
            WebApiKeys.COUPONS_REQ->{
                try {
                    var jsonObject=JSONObject(response)
                    if (jsonObject.getBoolean("status")){
                        Log.e("Coupons", "====>>>$jsonObject")

                        var jsondata=jsonObject.getJSONObject("responseData")
                        if (!jsondata.isNull("coupons")){

                        }
                      //  couponAdapter.adddataInList()
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