package com.example.haggerplanet.views.savecards.savecardAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.AddressAdapterBinding
import com.example.haggerplanet.databinding.SavecardsAdpaterBinding
import com.example.haggerplanet.views.selectAddress.selectAddressAdapter.AddressAdapterVM
import kotlinx.android.synthetic.main.address_adapter.view.*



class SaveCardAdapter(var context: Context) : RecyclerView.Adapter<SaveCardAdapter.ViewHolder>() {

    lateinit var adapterwhishList: SavecardsAdpaterBinding
    var row_index =-1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterwhishList = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.savecards_adpater,
                parent,
                false
        )

        return ViewHolder(adapterwhishList!!)
    }

    override fun getItemCount(): Int {
        return 4
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

    inner class ViewHolder(var adapterwhishList: SavecardsAdpaterBinding) :
            RecyclerView.ViewHolder(adapterwhishList.root) {
        fun setData() {
            var notificationAdapterVM = SaveCardAdpaterVM(context)
            adapterwhishList.saveCardAdpaterVM = notificationAdapterVM
            adapterwhishList.executePendingBindings()
        }
    }

}