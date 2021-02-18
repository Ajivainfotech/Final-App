package com.example.haggerplanet.views.home

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel

class LanguageAlertVM(val context: Context, val languageAlert: AlertDialog):ViewModel() {

    fun cancelAlert(){
        languageAlert.dismiss()
    }
}