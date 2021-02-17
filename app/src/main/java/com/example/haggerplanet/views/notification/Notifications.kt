package com.example.haggerplanet.views.notification

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.databinding.FragmentNotificationBinding
import com.example.haggerplanet.views.home.Home
import kotlinx.android.synthetic.main.toolbar.view.*

class Notifications:Fragment() {
    lateinit var notificationsVM: NotificationsVM
    lateinit var notificationBinding: FragmentNotificationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notificationBinding=FragmentNotificationBinding.inflate(LayoutInflater.from(context))
        return notificationBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationsVM=NotificationsVM(context!!)
        notificationBinding.notificationsVM=notificationsVM
        setToolbar()
    }

    fun setToolbar(){
        Home.mainBinding.itemLayout.ivMenu.visibility=View.GONE
        Home.mainBinding.itemLayout.ivSearch.visibility=View.GONE
        Home.mainBinding.itemLayout.ivNotification.visibility=View.GONE
        Home.mainBinding.itemLayout.toolTitle.text="Notifications"
        Home.mainBinding.itemLayout.ivBack.visibility=View.VISIBLE
    }
}