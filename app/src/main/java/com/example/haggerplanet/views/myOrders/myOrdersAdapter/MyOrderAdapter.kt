package com.example.haggerplanet.views.myOrders.myOrdersAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterNotificationBinding
import com.example.haggerplanet.databinding.MyorderItemsBinding
import com.example.haggerplanet.views.notification.notificationAdapter.NotificationAdapterVM

class MyOrderAdapter(var context: Context) : RecyclerView.Adapter<MyOrderAdapter.ViewHolder>() {

    lateinit var myorderItemsBinding: MyorderItemsBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        myorderItemsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.myorder_items,
                parent,
                false
        )

        return ViewHolder(myorderItemsBinding!!)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData()
    }

    inner class ViewHolder(var myorderItemsBinding: MyorderItemsBinding) :
            RecyclerView.ViewHolder(myorderItemsBinding.root) {
        fun setData() {
            var notificationAdapterVM = MyOrderAdapterVM(context)
            myorderItemsBinding.myOrderAdapterVM = notificationAdapterVM
            myorderItemsBinding.executePendingBindings()
        }
    }
}