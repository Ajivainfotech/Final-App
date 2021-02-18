package com.example.haggerplanet.views.savecards

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.databinding.SaveCardsBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class SaveCard:Fragment(){
    lateinit var saveCardsVM: SaveCardsVM
    lateinit var saveCardsBinding: SaveCardsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        saveCardsBinding= SaveCardsBinding.inflate(LayoutInflater.from(context))
        return saveCardsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveCardsVM= SaveCardsVM(context!!)
        saveCardsBinding.saveCardsVM=saveCardsVM
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Save Cards"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }

}