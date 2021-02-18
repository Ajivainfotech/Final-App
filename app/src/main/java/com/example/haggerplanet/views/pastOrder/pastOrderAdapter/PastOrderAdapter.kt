package com.example.haggerplanet.views.pastOrder.pastOrderAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.CurrentOrderAdapterBinding
import com.example.haggerplanet.databinding.PastOrderAdapterBinding
import com.example.haggerplanet.views.currentOrder.currentOrderAdapter.CurrentOrderAdapterVM


class PastOrderAdapter(var context: Context) : RecyclerView.Adapter<PastOrderAdapter.ViewHolder>() {

    lateinit var adapterwhishList: PastOrderAdapterBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.past_order_adapter,
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

    inner class ViewHolder(var adapterwhishList: PastOrderAdapterBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = PastOrderAdapterVM(context)
            adapterwhishList.pastOrderAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

}