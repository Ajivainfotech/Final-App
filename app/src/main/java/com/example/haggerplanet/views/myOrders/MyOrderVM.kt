package com.example.haggerplanet.views.myOrders

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.contactUs.ConactUs
import com.example.haggerplanet.views.contactUs.ContactUsVM
import com.example.haggerplanet.views.currentOrder.CurrentOrder
import com.example.haggerplanet.views.myOrders.myOrdersAdapter.MyOrderAdapter
import com.example.haggerplanet.views.pastOrder.PastOrder

class MyOrderVM(val context: Context):ViewModel() {

    var myOrderAdapter=MyOrderAdapter(context)

    fun onClickCurrentOrder(){
        MethodsUtil.loadFragment(context,CurrentOrder())
    }

    fun onClickPastOrder(){
        MethodsUtil.loadFragment(context,PastOrder())

    }

    fun conClickCustomerCare(){
        MethodsUtil.loadFragment(context, ConactUs())

    }
}