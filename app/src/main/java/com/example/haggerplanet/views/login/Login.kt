package com.example.haggerplanet.views.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ActivityLoginBinding

class Login:AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding
    lateinit var loginVM: LoginVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding= DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginVM= LoginVM(this,this)
        loginBinding.loginVM=loginVM
    }
}