package com.example.haggerplanet.views.coupon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AddressAdapterBinding
import com.example.haggerplanet.databinding.CouponItemsBinding
import com.example.haggerplanet.views.category.CategoryVM
import com.example.haggerplanet.views.selectAddress.selectAddressAdapter.AddressAdapterVM



class CouponAdapter(var context: Context) : RecyclerView.Adapter<CouponAdapter.ViewHolder>() {

    lateinit var adapterwhishList: CouponItemsBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.coupon_items,
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

    inner class ViewHolder(var adapterwhishList: CouponItemsBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = CouponAdapterVM(context)
            adapterwhishList.couponAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }
    fun adddataInList( list:ArrayList<CategoryVM.CategoryModel>){

        if (!list.isNullOrEmpty()){
           // list1.clear()
          //  list1.addAll(list)
            notifyDataSetChanged()
        }

    }
}