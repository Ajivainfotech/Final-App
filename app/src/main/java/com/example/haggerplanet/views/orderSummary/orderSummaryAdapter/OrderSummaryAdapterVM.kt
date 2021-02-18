package com.example.haggerplanet.views.orderSummary.orderSummaryAdapter

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ItemsAdapterBinding

class OrderSummaryAdapterVM(val context: Context, val adapterwhishList: ItemsAdapterBinding):ViewModel() {


    var quantity=ObservableField("")
    init {
        setSpinner()
    }

    fun onclickSpinner(){

        adapterwhishList.SpinnerQuantiity.performClick()
    }
    fun setSpinner(){

        var list=ArrayList<String>()
        list.add("Select Quantity")
        list.add("Quantity : 1")
        list.add("Quantity : 2")
        list.add("Quantity : 3")
        list.add("Quantity : 4")
        list.add("Quantity : 5")
        list.add("Quantity : 6")
        var adapterReasons: ArrayAdapter<String>?= null
        adapterReasons = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, list)
        adapterwhishList.SpinnerQuantiity.adapter = adapterReasons
        adapterwhishList.SpinnerQuantiity.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position>0){
                    quantity.set(list[position])
                    Log.e("ApplyLeave", "Selected Reason: "+list[position])

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })
    }
}