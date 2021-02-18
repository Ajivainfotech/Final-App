package com.example.haggerplanet.views.pastOrder

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.views.pastOrder.pastOrderAdapter.PastOrderAdapter

class PastOrderVM(val context: Context):ViewModel() {
    var pastOrderAdapter= PastOrderAdapter(context)
}