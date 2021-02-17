package com.example.haggerplanet.views.relatedProducts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterWhislistBinding
import com.example.haggerplanet.databinding.RelatedProductsBinding
import com.example.haggerplanet.views.wishList.wishListAdapter.WishListAdapter
import com.example.haggerplanet.views.wishList.wishListAdapter.WishListAdapterVM



class RelatedProductsAdapter(var context: Context) : RecyclerView.Adapter<RelatedProductsAdapter.ViewHolder>() {

    lateinit var adapterwhishList: RelatedProductsBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.related_products,
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

    inner class ViewHolder(var adapterwhishList: RelatedProductsBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = RelatedProductsAdapterVM(context)
            adapterwhishList.relatedProductsAdapterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

}