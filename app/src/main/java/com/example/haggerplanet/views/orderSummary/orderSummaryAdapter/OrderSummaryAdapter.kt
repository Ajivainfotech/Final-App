package com.example.haggerplanet.views.orderSummary.orderSummaryAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ItemsAdapterBinding


class OrderSummaryAdapter(var context: Context) : RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder>() {

    lateinit var adapterwhishList: ItemsAdapterBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.items_adapter,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData()
    }

    inner class ViewHolder(var adapterwhishList: ItemsAdapterBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = OrderSummaryAdapterVM(context,adapterwhishList)
            adapterwhishList.orderSummaryAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

}