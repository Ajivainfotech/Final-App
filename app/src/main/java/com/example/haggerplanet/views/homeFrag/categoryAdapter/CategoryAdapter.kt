package com.example.haggerplanet.views.homeFrag.categoryAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterHomeBinding
import com.example.haggerplanet.views.homeFrag.HomeFragVM


class CategoryAdapter(var context: Context) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var categoryList=ArrayList<HomeFragVM.CategoryModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var adapterHomeBinding:AdapterHomeBinding= DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.adapter_home,
            parent,
            false
        )
        return ViewHolder(adapterHomeBinding!!.root,adapterHomeBinding)
    }

    override fun getItemCount(): Int {
        Log.e("CategoryAdapter","Listsize"+categoryList.size)
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model=categoryList[position]
        holder.setData(model,position)
    }

    inner class ViewHolder(var view: View,  var adapterHomeBinding:AdapterHomeBinding) : RecyclerView.ViewHolder(view){

        fun setData(
            model: HomeFragVM.CategoryModel,
            position: Int
        ) {

            val shopByCategoryAdapterVM=HomeCategoryAdapterVM(context,model,position)
            adapterHomeBinding.homeCategoryAdapterVM=shopByCategoryAdapterVM
            adapterHomeBinding.executePendingBindings()
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