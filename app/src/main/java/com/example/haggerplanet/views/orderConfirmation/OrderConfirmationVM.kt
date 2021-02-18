package com.example.haggerplanet.views.orderConfirmation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.orderStatus.OrderStatus

class OrderConfirmationVM(val context: Context):ViewModel() {

    fun onclickviewdetails(){
        MethodsUtil.loadFragment(context,OrderStatus())

    }
}