package com.example.haggerplanet.views.changePasswordFrag

import android.app.Activity
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class ChangePasswordFragVM(val context: Context): ViewModel()  {

    var newPassword=ObservableField("")
    var cnfPassword=ObservableField("")
    var oldPassword=ObservableField("")


    fun onClickBack(){
        (context as Activity).onBackPressed()
    }
}