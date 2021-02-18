package com.example.haggerplanet.views.cart.cartAdapter

import android.content.Context
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class CartAdapterVM(val context: Context):ViewModel() {

    var qty=ObservableInt(1)

    fun onClickIncrease(){
        if (qty.get()>=1){
            qty.set(qty.get()+1)
        }
    }

    fun onClickDescrease(){
        if (qty.get()>1){
            qty.set(qty.get()-1)
        }
    }
}