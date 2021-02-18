package com.example.haggerplanet.views.currentOrder

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.views.currentOrder.currentOrderAdapter.CurrentOrderAdapter

class CurrentOrderVM(val context: Context):ViewModel() {
    var currentOrderAdapter=CurrentOrderAdapter(context!!)

}