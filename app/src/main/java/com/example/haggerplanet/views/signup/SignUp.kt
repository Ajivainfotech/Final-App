package com.example.haggerplanet.views.signup

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ActivityMainBinding
import com.example.haggerplanet.databinding.ActivityRegisterAcitivtyBinding
import com.example.haggerplanet.views.home.HomeVM

class SignUp:AppCompatActivity() {

    lateinit var registerAcitivtyBinding: ActivityRegisterAcitivtyBinding
    lateinit var signUpVM: SignUpVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerAcitivtyBinding= DataBindingUtil.setContentView(this, R.layout.activity_register_acitivty)
        signUpVM= SignUpVM(this)
        registerAcitivtyBinding.signUpVM=signUpVM
    }
}