package com.example.haggerplanet.views.homeFrag.moreProductAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterMoreproductBinding
import com.example.haggerplanet.views.homeFrag.HomeFragVM


class MoreProductAdapter(var context: Context) :
        RecyclerView.Adapter<MoreProductAdapter.ViewHolder>() {

    var categoryList=ArrayList<HomeFragVM.MoreProductModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var adapterMoreproductBinding: AdapterMoreproductBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.adapter_moreproduct,
                parent,
                false
        )
        return ViewHolder(adapterMoreproductBinding!!.root,adapterMoreproductBinding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model=categoryList[position]
        holder.setData(model,position)
    }

    inner class ViewHolder(var view: View, var adapterMoreproductBinding: AdapterMoreproductBinding) : RecyclerView.ViewHolder(view){

        fun setData(
                model: HomeFragVM.MoreProductModel,
                position: Int
        ) {

            val moreProductAdapterVM= MoreProductAdapterVM(context,model,position)
            adapterMoreproductBinding!!.moreProductAdapterVM=moreProductAdapterVM
            adapterMoreproductBinding!!.executePendingBindings()
        }


    }

    fun addDatainList(list:ArrayList<HomeFragVM.MoreProductModel>){

        if (!list.isNullOrEmpty()) {
            categoryList.clear()
            categoryList.addAll(list)
            notifyDataSetChanged()

        }
    }
}