package com.example.haggerplanet.views.selectAddress

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.addAddress.AddAddress
import com.example.haggerplanet.views.orderSummary.OrderSummary
import com.example.haggerplanet.views.payment.PaymentInfo
import com.example.haggerplanet.views.selectAddress.selectAddressAdapter.AddressAdapter

class SelectAddressVM(val context: Context): ViewModel()  {
    var addressAdapter=AddressAdapter(context)

    fun onClickAddAdress(){
        var bundle=Bundle()
        bundle.putString("address","Add Address")
        var addAddress=AddAddress()
        addAddress.arguments=bundle
        MethodsUtil.loadFragment(context,addAddress)
    }

    fun onClickAddAdressEdit(){
        var bundle=Bundle()
        bundle.putString("address","Edit Address")
        var addAddress=AddAddress()
        addAddress.arguments=bundle
        MethodsUtil.loadFragment(context,addAddress)
    }


    fun onClickPayment(){
        MethodsUtil.loadFragment(context, OrderSummary())
    }
}