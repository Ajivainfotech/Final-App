package com.example.haggerplanet.views.cart.cartAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.CartRecycleviewDesignBinding
import com.example.haggerplanet.databinding.RelatedProductsBinding
import com.example.haggerplanet.views.relatedProducts.RelatedProductsAdapterVM


class CartAdapter(var context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    lateinit var adapterwhishList: CartRecycleviewDesignBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.cart_recycleview_design,
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

    inner class ViewHolder(var adapterwhishList: CartRecycleviewDesignBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = CartAdapterVM(context)
            adapterwhishList.cartAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

}