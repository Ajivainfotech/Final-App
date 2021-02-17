package com.example.haggerplanet.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.haggerplanet.R

import kotlinx.android.synthetic.main.custom_alert_msgs.view.*


object CustomAlerts {


    /* Alert Dialog with one button*/
    var customDialog: androidx.appcompat.app.AlertDialog? = null

    fun commonAlert(context: Context, message: String)
    {

        if (customDialog != null) {
            customDialog!!.dismiss()
        }

        var customAlertBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
        val customAlertView =
            LayoutInflater.from(context).inflate(R.layout.custom_alert_msgs, null)
        customAlertBuilder.setView(customAlertView)

        customAlertView.tvCustomAlertMessage.text = message
        customDialog = customAlertBuilder.create()
        customDialog!!.setCancelable(false)
        customDialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
        customDialog!!.show()

        customDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        customAlertView.cardCustomAlertOk.setOnClickListener {
            customDialog!!.dismiss()
        }

    }

    fun commonAlertWithIntent(context: Context, message: String, aClass: Class<*>) {

        if (customDialog != null) {
            customDialog!!.dismiss()
        }

        val customAlertBuilder = androidx.appcompat.app.AlertDialog.Builder(context)

        val customAlertView =
            LayoutInflater.from(context).inflate(R.layout.custom_alert_msgs, null)
        customAlertBuilder.setView(customAlertView)
        //alertDialogBuilder.setTitle(context.getString(R.string.app_name))
        customAlertView.tvCustomAlertMessage.text = message
        customDialog = customAlertBuilder.create()
        customDialog!!.setCancelable(false)
        customDialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
        customDialog!!.show()

        customDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        customAlertView.cardCustomAlertOk.setOnClickListener {
            customDialog!!.dismiss()

            val intent = Intent(context, aClass)
            context.startActivity(intent)
            (context as Activity).finishAffinity()
        }

    }

}