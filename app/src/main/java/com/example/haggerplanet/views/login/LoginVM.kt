package com.example.haggerplanet.views.login

import android.content.Context
import android.content.Intent
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.forgotPassword.ForgotPassword
import com.example.haggerplanet.views.home.Home
import com.example.haggerplanet.views.signup.SignUp
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.MethodsUtil
import org.json.JSONObject

class LoginVM(val context: Context, val login: Login):ViewModel(),RetrofitResponse {

    var email:ObservableField<String> = ObservableField("")
    var password:ObservableField<String> = ObservableField("")
    var errorMessage = ""

    fun onClickLogin(){

        if (validation()){

            val jsonObject = JSONObject()
            jsonObject.put("email",email.get().toString().trim())
            jsonObject.put("password",password.get().toString())
            jsonObject.put("ln","en")
            RetrofitService(context,this,WebApiKeys.LOGIN,WebApiKeys.LOGIN_REQ,3,jsonObject)
                    .callService(true,"")
        }
        else
        {
            MethodsUtil.showToast(context,errorMessage)
        }
    }

    fun onClickForgotPassword(){

        context.startActivity(Intent(context,ForgotPassword::class.java))
        login.finish()
    }
    fun onClickRegister(){

        context.startActivity(Intent(context,SignUp::class.java))
        login.finish()
    }

    private fun validation():Boolean{

        if (email.get().toString().trim().isEmpty()){
            errorMessage = "Please enter your email."
            return false
        }
        else if (!email.get().toString().trim()
                .matches(Patterns.EMAIL_ADDRESS.pattern().toRegex())
        ){

            errorMessage = "Please enter valid email address."
            return false
        }

        else if (password.get().toString().trim().isEmpty()){

            errorMessage = "Please enter your password."
            return false

        }
        else if (password.get().toString().trim().length<5 || password.get().toString().trim().length>15){

            errorMessage = "Password must contain atleast 5 chracters."
            return false
        }
        return true
    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {
        when(requestCode){

            WebApiKeys.LOGIN_REQ ->{

                try {

                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean("status")){

                        if (!jsonObject.isNull("responseData")){

                            val dataObject = jsonObject.getJSONObject("responseData")
                            PrefferenceFile.storeKey(context,CommonKeys.USER_ID,dataObject.getString("id"))
                            PrefferenceFile.storeKey(context,CommonKeys.USER_EMAIL,dataObject.getString("email"))
                            PrefferenceFile.storeKey(context,CommonKeys.USER_NAME,dataObject.getString("name"))
                            PrefferenceFile.storeKey(context,CommonKeys.USER_PHONE_NUMBER,dataObject.getString("mobile"))
                        }
                        MethodsUtil.showToast(context,jsonObject.getString("responseMessage"))
                        context.startActivity(Intent(context,Home::class.java))
                        login.finish()

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