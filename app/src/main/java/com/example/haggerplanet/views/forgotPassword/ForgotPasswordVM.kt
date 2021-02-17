package com.example.haggerplanet.views.forgotPassword

import android.content.Context
import android.content.Intent
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.retrofit.RetrofitResponse

import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.MethodsUtil
import org.json.JSONObject

class ForgotPasswordVM(val context: Context, val forgotPassword: ForgotPassword):ViewModel(),RetrofitResponse {
    var email:ObservableField<String> = ObservableField("")
    var errorMessage = ""



    fun onClickForgotPassword(){
        if (validation()){

            val jsonObject= JSONObject()
            jsonObject.put("email",email.get().toString())
            RetrofitService(context,this,WebApiKeys.FORGOT_PASSWORD,WebApiKeys.FORGOT_PASSWORD_REQ,3,jsonObject)
                .callService(true,"")
        }
        else
        {
            MethodsUtil.showToast(context,errorMessage)
        }

    }
    private fun validation():Boolean{

        if (email.get()!!.toString().trim().isEmpty()){

            errorMessage = "Please enter your registered email address."
            return false
        }
        else if (!email.get().toString().trim()
                .matches(Patterns.EMAIL_ADDRESS.pattern().toRegex())
            ){

            errorMessage = "Please enter valid email address."
            return false
        }


        return true
    }
    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {
        when(requestCode){

            WebApiKeys.FORGOT_PASSWORD_REQ ->{

                try {

                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean("status")){
                        val intent = Intent(context,ResetPassword::class.java)
                        intent.putExtra("email",email.get().toString())
                        context.startActivity(intent)
                        forgotPassword.finish()
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