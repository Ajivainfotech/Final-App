package com.example.haggerplanet.views.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ActivitySplashBinding
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.views.home.Home
import com.example.haggerplanet.views.login.Login


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)


        Handler().postDelayed({
            goToMain()
        }, 2000)
    }

    private fun goToMain() {

        if (PrefferenceFile.retrieveKey(this,CommonKeys.USER_ID)!!.isNotEmpty()){
            val i = Intent(this@Splash, Home::class.java)
            startActivity(i)
            finish()

        }else{
            val i = Intent(this@Splash, Login::class.java)
            startActivity(i)
            finish()
        }

    }
}