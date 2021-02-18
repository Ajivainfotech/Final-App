package com.example.haggerplanet.views.currentOrder.currentOrderAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.CouponItemsBinding
import com.example.haggerplanet.databinding.CurrentOrderAdapterBinding
import com.example.haggerplanet.views.coupon.CouponAdapterVM

class CurrentOrderAdapter(var context: Context) : RecyclerView.Adapter<CurrentOrderAdapter.ViewHolder>() {

    lateinit var adapterwhishList: CurrentOrderAdapterBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.current_order_adapter,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData()
    }

    inner class ViewHolder(var adapterwhishList: CurrentOrderAdapterBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = CurrentOrderAdapterVM(context)
            adapterwhishList.currentOrderAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

}