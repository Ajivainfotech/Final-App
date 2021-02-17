package com.example.haggerplanet.views.productList.productListAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ProductListAdapterBinding
import com.example.haggerplanet.views.category.CategoryVM

class ProductListAdapter(var context: Context) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    lateinit var adapterwhishList: ProductListAdapterBinding
    var list1=ArrayList<CategoryVM.CategoryModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.product_list_adapter,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        Log.e("getItemCountListSize","====>>>"+list1.size)
        return list1.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list1[position])
    }

    inner class ViewHolder(var adapterwhishList: ProductListAdapterBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData(model: CategoryVM.CategoryModel) {
            var notificationAdapterVM = ProductListAdpterVM(context,model)
            adapterwhishList.productListAdpterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

    fun adddataInList( list:ArrayList<CategoryVM.CategoryModel>){

        if (!list.isNullOrEmpty()){
            list1.clear()
            list1.addAll(list)
            notifyDataSetChanged()
        }

    }
}