package com.example.haggerplanet.views.homeFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentHomeBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class HomeFrag:Fragment() {

    lateinit var homeFragVM: HomeFragVM
    lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding= FragmentHomeBinding.inflate(LayoutInflater.from(context))
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragVM=HomeFragVM(context!!,this)
        homeBinding.homeFragVM=homeFragVM
    }

    override fun onResume() {
        super.onResume()
        homeFragVM.setupAutoPager()
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.toolTitle.text="Dashboard"
        Home.mainBinding.itemLayout.ivSearch.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.ivBack.visibility=View.GONE
    }
}