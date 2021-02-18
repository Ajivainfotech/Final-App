package com.example.haggerplanet.views.selectAddress.selectAddressAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AddressAdapterBinding
import com.example.haggerplanet.databinding.CartRecycleviewDesignBinding
import com.example.haggerplanet.views.cart.cartAdapter.CartAdapterVM
import kotlinx.android.synthetic.main.address_adapter.view.*


class AddressAdapter(var context: Context) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    lateinit var adapterwhishList: AddressAdapterBinding
    var row_index =-1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.address_adapter,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            radioButton.setOnClickListener {
                row_index=position
                notifyDataSetChanged()
            }

            radioButton.isChecked = position == row_index
        }


        holder.setData()
    }

    inner class ViewHolder(var adapterwhishList: AddressAdapterBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = AddressAdapterVM(context)
            adapterwhishList.addressAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

}