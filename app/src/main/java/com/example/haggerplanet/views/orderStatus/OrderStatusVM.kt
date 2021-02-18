package com.example.haggerplanet.views.orderStatus

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.cancelOrder.CancelOrder

class OrderStatusVM(val context: Context):ViewModel() {

    fun onClickContiue(){

        MethodsUtil.loadFragment(context,CancelOrder())
    }
}