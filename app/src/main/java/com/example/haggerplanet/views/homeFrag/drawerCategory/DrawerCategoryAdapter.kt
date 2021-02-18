package com.example.haggerplanet.views.homeFrag.drawerCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.DrawerCategoryAdapterBinding
import com.example.haggerplanet.views.homeFrag.HomeFragVM


class DrawerCategoryAdapter(var context: Context) :
        RecyclerView.Adapter<DrawerCategoryAdapter.ViewHolder>() {

    var categoryList=ArrayList<HomeFragVM.CategoryModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var adapterHomeBinding: DrawerCategoryAdapterBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.drawer_category_adapter,
                parent,
                false
        )
        return ViewHolder(adapterHomeBinding!!.root,adapterHomeBinding)
    }

    override fun getItemCount(): Int {

        if(categoryList.size>5){
            return 5
        }
       return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model=categoryList[position]
        holder.setData(model,position)
    }

    inner class ViewHolder(var view: View, var adapterHomeBinding: DrawerCategoryAdapterBinding) : RecyclerView.ViewHolder(view){

        fun setData(
                model: HomeFragVM.CategoryModel,
                position: Int
        ) {

            val shopByCategoryAdapterVM= DrawerCategoryAdapterVM(context,model,position)
            adapterHomeBinding!!.drawerCategoryAdapterVM=shopByCategoryAdapterVM
            adapterHomeBinding!!.executePendingBindings()
        }


    }

    fun addDatainList(list:ArrayList<HomeFragVM.CategoryModel>){

        if (!list.isNullOrEmpty()) {
            categoryList.clear()
            categoryList.addAll(list)
            notifyDataSetChanged()

        }
    }
}