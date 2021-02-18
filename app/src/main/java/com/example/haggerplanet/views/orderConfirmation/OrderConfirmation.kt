package com.example.haggerplanet.views.orderConfirmation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.OrderConfirmationBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.order_confirmation.*
import kotlinx.android.synthetic.main.toolbar.view.*

class OrderConfirmation:Fragment() {
    lateinit var orderConfirmationVM: OrderConfirmationVM
    lateinit var orderConfirmationBinding: OrderConfirmationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        orderConfirmationBinding= OrderConfirmationBinding.inflate(LayoutInflater.from(context))
        return orderConfirmationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderConfirmationVM=OrderConfirmationVM(context!!)
        orderConfirmationBinding.orderConfirmationVM=orderConfirmationVM
        setToolbar()
    }


    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Orders Confirmation"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
        getBundleData()
    }

    fun getBundleData(){
        if (this.arguments!=null){
            var data=this.arguments
            if (data!!.containsKey("cancel")){
                var cancelOrder=data.getString("cancel")
                Home.mainBinding.itemLayout.toolTitle.text=cancelOrder
                tvOrderPlced.text=cancelOrder

            }
        }
    }
}