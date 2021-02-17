package com.example.haggerplanet.views.forgotPassword

import android.content.Context
import android.content.Intent
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.login.Login
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.MethodsUtil
import org.json.JSONObject

class ResetPasswordVM(var context: Context,var resetPassword: ResetPassword):ViewModel(),RetrofitResponse {

    var otp:ObservableField<String> = ObservableField("")
    var newPassword:ObservableField<String> = ObservableField("")
    var cnfPassword:ObservableField<String> = ObservableField("")
    var errorMessage = ""
    var email = ""

    init {
        email =resetPassword.intent.getStringExtra("email")!!
    }
  fun onClickResetPassword(){
      if (validation()){
          val jsonObject = JSONObject()
          jsonObject.put("email",email)
          jsonObject.put("otp",otp.get().toString())
          jsonObject.put("password",newPassword.get().toString())
          RetrofitService(context,this,WebApiKeys.RESET_PASSWORD,WebApiKeys.RESET_PASSWORD_REQ,3,jsonObject)
                  .callService(true,"")
      }
      else
      {
          MethodsUtil.showToast(context,errorMessage)
      }

  }

    private fun validation():Boolean{

        when {
            otp.get().toString().trim().isEmpty() -> {

                errorMessage = "Please enter OTP !"
                return false
            }
            newPassword.get().toString().trim().isEmpty() -> {

                errorMessage = "Please enter your new password."
                return false

            }
            newPassword.get().toString().trim().length<5 -> {

                errorMessage = "Password must contain atleast 5 chracters."

                return false

            }
            cnfPassword.get().toString().trim().isEmpty() -> {

                errorMessage = "Please enter confirm password."
                return false

            }
            cnfPassword.get().toString().trim().length<5 -> {
                errorMessage = "Password must contain atleast 5 chracters."
                return false

            }
            newPassword.get().toString().trim()!= cnfPassword.get().toString().trim() -> {

                errorMessage = "Password doesn't match."
                return false
            }
            else -> return true
        }
    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {
        when(requestCode){

            WebApiKeys.RESET_PASSWORD_REQ ->{

                try {

                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean("status")){

                        context.startActivity(Intent(context,Login::class.java))
                        MethodsUtil.showToast(context,jsonObject.getString("responseMessage"))
                        resetPassword.finish()
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
        TODO("Not yet implemented")
    }

}