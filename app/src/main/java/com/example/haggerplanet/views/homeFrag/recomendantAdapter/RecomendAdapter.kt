package com.example.haggerplanet.views.homeFrag.recomendantAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AdapterRecomendBinding
import com.example.haggerplanet.views.homeFrag.HomeFragVM

class RecomendAdapter(var context: Context) :
        RecyclerView.Adapter<RecomendAdapter.ViewHolder>() {

    var categoryList=ArrayList<HomeFragVM.RecommendantModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var adapterRecomendBinding: AdapterRecomendBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.adapter_recomend,
                parent,
                false
        )
        return ViewHolder(adapterRecomendBinding!!.root,adapterRecomendBinding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model=categoryList[position]
        holder.setData(model,position)
    }

    inner class ViewHolder(var view: View, var adapterRecomendBinding: AdapterRecomendBinding) : RecyclerView.ViewHolder(view){

        fun setData(
                model: HomeFragVM.RecommendantModel,
                position: Int
        ) {

            val recomendAdapterVM= RecomendAdapterVM(context,model,position)
            adapterRecomendBinding!!.recomendAdapterVM=recomendAdapterVM
            adapterRecomendBinding!!.executePendingBindings()
        }


    }

    fun addDatainList(list:ArrayList<HomeFragVM.RecommendantModel>){

        if (!list.isNullOrEmpty()) {
            categoryList.clear()
            categoryList.addAll(list)
            notifyDataSetChanged()

        }
    }
}