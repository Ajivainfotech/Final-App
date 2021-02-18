package com.example.haggerplanet.views.orderSummary

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.addAddress.AddAddress
import com.example.haggerplanet.views.orderSummary.orderSummaryAdapter.OrderSummaryAdapter
import com.example.haggerplanet.views.payment.PaymentInfo

class OrderSummaryVM(val context: Context):ViewModel() {
    var orderSummaryAdapter= OrderSummaryAdapter(context)

    fun onClickContiue(){
        MethodsUtil.loadFragment(context,PaymentInfo())
    }

    fun onClickAddAdressEdit(){
        var bundle= Bundle()
        bundle.putString("address","Edit Address")
        var addAddress= AddAddress()
        addAddress.arguments=bundle
        MethodsUtil.loadFragment(context,addAddress)
    }
}