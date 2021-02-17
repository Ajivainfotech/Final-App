package com.example.haggerplanet.views.notification.notificationAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterNotificationBinding


class NotificationAdapter(var context: Context) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    lateinit var notificationBinding:AdapterNotificationBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        notificationBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.adapter_notification,
                parent,
                false
        )

        return ViewHolder(notificationBinding!!)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData()
    }

    inner class ViewHolder(var notificationBinding: AdapterNotificationBinding) :
            RecyclerView.ViewHolder(notificationBinding.root) {
        fun setData() {
            var notificationAdapterVM = NotificationAdapterVM(context)
            notificationBinding.notificationAdapterVM = notificationAdapterVM
            notificationBinding.executePendingBindings()
        }
    }
}