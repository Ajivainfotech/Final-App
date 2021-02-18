package com.example.haggerplanet.views.payment

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.orderConfirmation.OrderConfirmation

class PaymentVM(val context: Context):ViewModel() {

    fun onclickProceed(){
        MethodsUtil.loadFragment(context,OrderConfirmation())
    }
}