package com.example.haggerplanet.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.haggerplanet.views.login.Login



object PrefferenceFile {
    internal lateinit var sharedPreferences: SharedPreferences
    internal lateinit var editor: SharedPreferences.Editor
    val SHARED_PREFERENCE_KEY = "Haggler"
    val FCM_PREFERENCE_KEY = "Haggler"
    val LOGIN_KEYS = "login_values"



    fun storeKey(context: Context, key: String, value: String) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()

    }

    fun storeRememberKey(context: Context, key: String, value: String) {
        sharedPreferences =
            context.getSharedPreferences(LOGIN_KEYS, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()

    }

    fun retrieveRememberKey(context: Context, key: String): String? {
        sharedPreferences =
            context.getSharedPreferences(LOGIN_KEYS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }



    fun EditstoreKey(context: Context, key: String, value: String) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()

    }

    fun storeIntKey(context: Context, key: String, value: Int) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()

    }

    fun storeBooleanKey(context: Context, key: String, value: Boolean) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.commit()

    }

    fun retrieveBooleanKey(context: Context, key: String, value: Boolean):Boolean? {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key,value)

    }

    fun retrieveIntKey(context: Context, key: String): String? {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun retrieveKey(context: Context, key: String): String? {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

    fun EditretrieveKey(context: Context, key: String): String? {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

    fun removeAll(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

    fun removekey(context: Context, key: String) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.remove(key)
        editor.commit()
    }




    /*To Store Fcm Device ID*/
    fun storeFcmDeviceId(
        context: Context,
        token: String?
    ) {
        sharedPreferences = context.getSharedPreferences(FCM_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString(CommonKeys.token, token)
        editor.commit()
    }

    fun retrieveFcmDeviceId(context: Context): String? {
        sharedPreferences = context.getSharedPreferences(FCM_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return sharedPreferences.getString(CommonKeys.token, null)
    }

    fun clearPref(context: Context){

        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }



    fun logout(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
        context.startActivity(
            Intent(
                context,
                Login::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
        (context as Activity).finish()
    }
}