package com.example.haggerplanet.views.addAddress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.AddAddressBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class AddAddress:Fragment() {
    lateinit var addAddressVM: AddAddressVM
    lateinit var addressBinding: AddAddressBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        addressBinding= AddAddressBinding.inflate(LayoutInflater.from(context))
       return addressBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAddressVM= AddAddressVM(context!!)
        addressBinding.addAddressVM=addAddressVM
    }

    fun getBundleData(){
        if (this.arguments!=null){
            var data=this.arguments
            if (data!!.containsKey("address")){
                var addressType=data.getString("address")
                Home.mainBinding.itemLayout.toolTitle.text=addressType
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Add address"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
        getBundleData()
    }
}