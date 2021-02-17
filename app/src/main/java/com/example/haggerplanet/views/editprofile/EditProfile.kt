package com.example.haggerplanet.views.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentProfileDetailBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class EditProfile:Fragment() {
    lateinit var editProfileVM: EditProfileVM
    lateinit var profileDetailBinding: FragmentProfileDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileDetailBinding=FragmentProfileDetailBinding.inflate(LayoutInflater.from(context))
        return profileDetailBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editProfileVM= EditProfileVM(context!!,this)
        profileDetailBinding.editProfileVM=editProfileVM
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Edit Profile"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
    }
}