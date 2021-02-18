package com.example.haggerplanet.views.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentProfileBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class Profile:Fragment() {
    lateinit var profileFragVM:ProfileFragVM
    lateinit var profileBinding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileBinding=FragmentProfileBinding.inflate(LayoutInflater.from(context!!))
        return profileBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileFragVM=ProfileFragVM(context!!)
        profileBinding.profileFragVM=profileFragVM
        setToolbar()
    }


    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Account Info"
        Home.mainBinding.itemLayout.ivBack.visibility=View.GONE
        Home.mainBinding.itemLayout.tvLang.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.VISIBLE
    }
}