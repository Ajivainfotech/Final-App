package com.example.haggerplanet.views.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.R
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.login.Login
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.MethodsUtil
import kotlinx.android.synthetic.main.dia_otp_verify.view.*
import org.json.JSONObject

class SignUpVM(val context: Context):ViewModel(),RetrofitResponse {

   var name:ObservableField<String> = ObservableField("")
   var email:ObservableField<String> = ObservableField("")
   var mobile:ObservableField<String> = ObservableField("")
   var password:ObservableField<String> = ObservableField("")
    var errorMessage:String = ""
    var otp:ObservableField<String> = ObservableField("")
    var customDialog: androidx.appcompat.app.AlertDialog? = null


    fun onClickLogin(){

        context.startActivity(Intent(context,Login::class.java))
    }

    fun onCLickSignUp(){

        if (validation()){

            val jsonObject = JSONObject()
            jsonObject.put("name",name.get().toString())
            jsonObject.put("email",email.get().toString())
            jsonObject.put("password",password.get().toString())
            jsonObject.put("phone",mobile.get().toString())
            jsonObject.put("Ln","en")
            RetrofitService(context,this,WebApiKeys.SIGN_UP,WebApiKeys.SIGN_UP_REQ,3,jsonObject)
                    .callService(true,"")
        }
        else
        {
            MethodsUtil.showToast(context,errorMessage)
        }
    }
    private fun validation():Boolean{
        if (name.get().toString().trim().isEmpty()){
            errorMessage = "Please enter your name."
            return false
        }
        else if (email.get().toString().trim().isEmpty()){

            errorMessage = "Please enter your email address."
            return false
        }
        else if (!email.get().toString().trim()
                .matches(Patterns.EMAIL_ADDRESS.pattern().toRegex())
        ){

            errorMessage = "Please enter valid email address."
            return false
        }

        else if (mobile.get().toString().trim().isEmpty()){

            errorMessage = "Please enter your mobile number."

            return false
        }
        else if (mobile.get().toString().trim().length<5 || mobile.get().toString().trim().length>15){

            errorMessage = "Please enter valid mobile number."

            return false
        }
        else if (password.get().toString().trim().isEmpty()){

            errorMessage = "Please enter password."

            return false
        }
        else if (password.get().toString().trim().length<5){

            errorMessage = "Password must contain atleast 5 chracters."

            return false
        }

        return true
    }

    private fun openOtpAlert() {
        val customAlertBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
        val customAlertView =
                LayoutInflater.from(context).inflate(R.layout.dia_otp_verify, null)
        customAlertBuilder.setView(customAlertView)
        customDialog = customAlertBuilder.create()
        customDialog!!.show()

        customDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog!!.setCancelable(false)
        customDialog!!.setCanceledOnTouchOutside(false)


        customAlertView.btResetPassSubmit.setOnClickListener {
            if (customAlertView.edResetotp.toString().trim().isEmpty()) {

                MethodsUtil.showToast(context,"Please enter otp.")
            }
            else if (customAlertView.edResetotp.toString().trim().length<6)
            {
                MethodsUtil.showToast(context,"Please enter valid otp.")
            }
            else {

                val jsonObject = JSONObject()
                jsonObject.put("email",email.get().toString())
                jsonObject.put("otp",customAlertView.edResetotp.text.toString().toInt())
                jsonObject.put("ln","en")
                RetrofitService(context,this,WebApiKeys.OTP_VERIFY,WebApiKeys.OTP_VERIFY_REQ,3,jsonObject)
                        .callService(true,"")
            }
        }

    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {
        when(requestCode){

            WebApiKeys.SIGN_UP_REQ ->{

                try {

                    val jsonObject = JSONObject(response)
                    Log.e("SignUpResponse",jsonObject.toString())
                    if (jsonObject.getBoolean("status")){

                        openOtpAlert()
                    }
                    else
                    {
                        MethodsUtil.showToast(context,jsonObject.getString("responseMessage"))
                    }
                }
                catch (e:Exception){

                    e.printStackTrace()
                }
            }

            WebApiKeys.OTP_VERIFY_REQ ->{

                try {

                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean("status")){

                        customDialog!! .dismiss()
                        context.startActivity(Intent(context,Login::class.java))
                        MethodsUtil.showToast(context,jsonObject.getString("responseMessage"))
                        (context as Activity).finish()
                    }
                    else
                    {
                        MethodsUtil.showToast(context,jsonObject.getString("responseMessage"))
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