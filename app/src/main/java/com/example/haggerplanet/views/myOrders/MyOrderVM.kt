package com.example.haggerplanet.views.myOrders

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.views.myOrders.myOrdersAdapter.MyOrderAdapter

class MyOrderVM(val context: Context):ViewModel() {

    var myOrderAdapter=MyOrderAdapter(context)
}