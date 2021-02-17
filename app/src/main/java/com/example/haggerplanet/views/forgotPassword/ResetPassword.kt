package com.example.haggerplanet.views.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ActivityForgotPasswordBinding
import com.example.haggerplanet.databinding.ChangePasswordBinding

class ResetPassword : AppCompatActivity() {
    lateinit var activityResetPasswordBinding: ChangePasswordBinding
    lateinit var resetPasswordVM: ResetPasswordVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResetPasswordBinding= DataBindingUtil.setContentView(this, R.layout.change_password)
        resetPasswordVM= ResetPasswordVM(this,this)
        activityResetPasswordBinding.resetPasswordVM=resetPasswordVM
    }
}