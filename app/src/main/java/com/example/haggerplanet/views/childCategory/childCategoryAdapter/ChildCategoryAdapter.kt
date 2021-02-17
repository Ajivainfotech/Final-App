package com.example.haggerplanet.views.childCategory.childCategoryAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ChildCategoryAdapterBinding
import com.example.haggerplanet.views.category.CategoryVM

class ChildCategoryAdapter(var context: Context) : RecyclerView.Adapter<ChildCategoryAdapter.ViewHolder>() {

    var list1=ArrayList<CategoryVM.CategoryModel>()
    lateinit var adapterwhishList: ChildCategoryAdapterBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.child_category_adapter,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        Log.e("ChildCategpry","Adapter"+list1.size)
        return list1.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list1[position],position)
    }

    inner class ViewHolder(var adapterwhishList: ChildCategoryAdapterBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData(categoryModel: CategoryVM.CategoryModel, position: Int) {
            var notificationAdapterVM = ChildcategoryAdapterVM(context,categoryModel,position)
            adapterwhishList.childcategoryAdapterVM = notificationAdapterVM
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