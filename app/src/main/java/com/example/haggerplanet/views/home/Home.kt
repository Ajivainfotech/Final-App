package com.example.haggerplanet.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.ActivityMainBinding
import com.example.haggerplanet.databinding.FragmentHomeBinding
import com.example.haggerplanet.databinding.ToolbarBinding
import com.example.haggerplanet.views.cart.Cart
import com.example.haggerplanet.views.homeFrag.HomeFrag
import com.example.haggerplanet.views.profile.Profile
import com.example.haggerplanet.views.sellFragment.SellFrag

class Home : AppCompatActivity() {

    companion object{

        lateinit var mainBinding: ActivityMainBinding
        lateinit var toolbarBinding: ToolbarBinding
        lateinit var homeVM: HomeVM

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        homeVM=HomeVM(this,this)
        mainBinding.homeVM=homeVM
    }

    override fun onBackPressed() {
        super.onBackPressed()

        var f = supportFragmentManager.findFragmentById(R.id.frameHome)

        when (f) {

            is HomeFrag -> {

                mainBinding.navigation.menu.getItem(0).isChecked = true
            }
            is SellFrag -> {

                mainBinding.navigation.menu.getItem(1).isChecked = true
            }
            is Cart -> {

                mainBinding.navigation.menu.getItem(2).isChecked = true
            }

            is Profile -> {

                mainBinding.navigation.menu.getItem(3).isChecked = true
            }

        }
    }
}