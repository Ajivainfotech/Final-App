package com.example.haggerplanet.views.pastOrder.pastOrderAdapter

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.contactUs.ConactUs
import com.example.haggerplanet.views.orderStatus.OrderStatus

class PastOrderAdapterVM(val context: Context):ViewModel() {

    fun onClickOrder(){
        MethodsUtil.loadFragment(context,OrderStatus())
    }

    fun onClickContaus(){
        MethodsUtil.loadFragment(context,ConactUs())
    }


}