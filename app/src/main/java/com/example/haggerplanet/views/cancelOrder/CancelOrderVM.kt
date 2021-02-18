package com.example.haggerplanet.views.cancelOrder

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.orderConfirmation.OrderConfirmation

class CancelOrderVM(val context: Context):ViewModel() {

    fun orderCancellation(){
        var bundle=Bundle()
        bundle.putString("cancel","Order Cancelled")
        var orderConfirmation=OrderConfirmation()
        orderConfirmation.arguments=bundle
        MethodsUtil.loadFragment(context,orderConfirmation)
    }
}