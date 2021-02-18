package com.example.haggerplanet.views.currentOrder.currentOrderAdapter

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.cancelOrder.CancelOrder
import com.example.haggerplanet.views.contactUs.ConactUs
import com.example.haggerplanet.views.helpCenter.HelpCenter
import com.example.haggerplanet.views.orderStatus.OrderStatus

class CurrentOrderAdapterVM(val context: Context):ViewModel() {

    fun onClickOrder(){
        MethodsUtil.loadFragment(context, OrderStatus())
    }

    fun onClickCancel(){
        MethodsUtil.loadFragment(context, CancelOrder())
    }

    fun onClickHelp(){
        MethodsUtil.loadFragment(context, ConactUs())
    }

}