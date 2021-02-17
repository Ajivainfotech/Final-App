package com.example.haggerplanet.views.notification

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.views.notification.notificationAdapter.NotificationAdapter

class NotificationsVM(val context: Context): ViewModel() {

    var notificationAdapter=NotificationAdapter(context)
}