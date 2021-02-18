package com.example.haggerplanet.views.savecards

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.haggerplanet.views.savecards.savecardAdapter.SaveCardAdapter

class SaveCardsVM(val context: Context):ViewModel() {
    var saveCardAdapter= SaveCardAdapter(context)
    var saveLayout=ObservableBoolean(false)

    fun onClickAddCards(){
        saveLayout.set(true)
    }
}