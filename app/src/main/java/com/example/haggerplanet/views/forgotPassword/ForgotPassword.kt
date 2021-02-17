package com.example.haggerplanet.views.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ActivityForgotPasswordBinding

class ForgotPassword : AppCompatActivity() {
    lateinit var activityForgotPasswordBinding: ActivityForgotPasswordBinding
    lateinit var forgotPasswordVM: ForgotPasswordVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityForgotPasswordBinding= DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        forgotPasswordVM= ForgotPasswordVM(this,this)
        activityForgotPasswordBinding.forgotPasswordVM=forgotPasswordVM
    }
}
