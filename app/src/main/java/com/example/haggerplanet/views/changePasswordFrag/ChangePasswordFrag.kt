package com.example.haggerplanet.views.changePasswordFrag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.databinding.ChangepasswordFragBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class ChangePasswordFrag:Fragment(){
    lateinit var changePasswordFragVM: ChangePasswordFragVM
    lateinit var changepasswordFragBinding: ChangepasswordFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        changepasswordFragBinding= ChangepasswordFragBinding.inflate(LayoutInflater.from(context!!))
        return changepasswordFragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changePasswordFragVM=ChangePasswordFragVM(context!!)
        changepasswordFragBinding.changePasswordFragVM=changePasswordFragVM
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Change Password"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
    }
}