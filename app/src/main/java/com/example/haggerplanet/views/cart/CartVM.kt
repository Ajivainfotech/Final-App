package com.example.haggerplanet.views.cart

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.views.cart.cartAdapter.CartAdapter
import com.example.haggerplanet.views.payment.PaymentInfo

class CartVM(val context: Context):ViewModel() {
     var cartAdapter=CartAdapter(context)

     fun onClickCheckOut(){
          MethodsUtil.loadFragment(context,PaymentInfo())
     }
}