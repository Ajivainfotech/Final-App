package com.example.haggerplanet.views.category.categoryAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterCategoryBinding
import com.example.haggerplanet.views.category.CategoryVM

class CategoryAdapter(var context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var list1=ArrayList<CategoryVM.CategoryModel>()
    lateinit var adapterwhishList: AdapterCategoryBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.adapter_category,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        return list1.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list1[position],position)
    }

    inner class ViewHolder(var adapterwhishList: AdapterCategoryBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData(categoryModel: CategoryVM.CategoryModel, position: Int) {
            var notificationAdapterVM = CategoryAdapterVM(context,categoryModel,position)
            adapterwhishList.categoryAdapterVM = notificationAdapterVM
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