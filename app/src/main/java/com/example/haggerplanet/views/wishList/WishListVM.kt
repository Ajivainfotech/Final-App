package com.example.haggerplanet.views.wishList

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.views.wishList.wishListAdapter.WishListAdapter

class WishListVM(val context: Context):ViewModel() {
    var wishListAdapter= WishListAdapter(context)
}