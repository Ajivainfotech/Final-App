package com.example.haggerplanet.views.termsAndConditions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.TermsAndConditionsBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class TermsAndConditions:Fragment() {
    lateinit var termsAndConitionsVM: TermsAndConitionsVM
    lateinit var termsAndConditionsBinding: TermsAndConditionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        termsAndConditionsBinding= TermsAndConditionsBinding.inflate(LayoutInflater.from(context))
        return termsAndConditionsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        termsAndConitionsVM=TermsAndConitionsVM(context!!,this)
        termsAndConditionsBinding.termsAndConitionsVM=termsAndConitionsVM
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }
    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Terms and Conditions"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }
}