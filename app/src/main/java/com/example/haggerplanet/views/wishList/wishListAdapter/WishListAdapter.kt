package com.example.haggerplanet.views.wishList.wishListAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterWhislistBinding

class WishListAdapter(var context: Context) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    lateinit var adapterwhishList: AdapterWhislistBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.adapter_whislist,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData()
    }

    inner class ViewHolder(var adapterwhishList: AdapterWhislistBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = WishListAdapterVM(context)
            adapterwhishList.wishListAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }
}