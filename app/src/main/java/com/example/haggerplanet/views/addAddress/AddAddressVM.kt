package com.example.haggerplanet.views.addAddress

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.orderSummary.OrderSummary
import com.example.haggerplanet.views.selectAddress.SelectAddress

class AddAddressVM(val context: Context):ViewModel() {

    fun onClickAddressSumbit(){
        MethodsUtil.loadFragment(context,OrderSummary())
    }
}