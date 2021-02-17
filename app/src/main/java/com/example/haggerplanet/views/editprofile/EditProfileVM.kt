package com.example.haggerplanet.views.editprofile

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.R
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.views.changePasswordFrag.ChangePasswordFrag
import com.example.haggerplanet.views.forgotPassword.ResetPassword
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class EditProfileVM(val context: Context,val editProfile: EditProfile):ViewModel() {

    var name= ObservableField("")
    var phone= ObservableField("")
    var email= ObservableField("")
    var gender= ObservableField("")
    var dob= ObservableField("")
    var location= ObservableField("")
    var date= ObservableField("")
    var date1=""

    init {
        name.set(PrefferenceFile.retrieveKey(context,CommonKeys.USER_NAME))
        email.set(PrefferenceFile.retrieveKey(context,CommonKeys.USER_EMAIL))
        phone.set(PrefferenceFile.retrieveKey(context,CommonKeys.USER_PHONE_NUMBER))
        setSpinner()
    }


    fun onClickGender() {
        editProfile.profileDetailBinding.SpinnerGender.performClick()
    }

    fun openDateTo() {

        openDatePicker(date)
    }

    fun setSpinner(){

        var list=ArrayList<String>()
        list.add("Select Gender")
        list.add("Male")
        list.add("FeMale")
        var adapterReasons: ArrayAdapter<String> ?= null
        adapterReasons = ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item, list)
        editProfile.profileDetailBinding.SpinnerGender.adapter = adapterReasons

        editProfile.profileDetailBinding.SpinnerGender.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position>0){
                    gender.set(list[position])
                    Log.e("ApplyLeave", "Selected Reason: "+list[position])

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })
    }

    private fun openDatePicker(dateToSet: ObservableField<String>) {

        var cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()

        var datePickerListener =
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                    date.set(MethodsUtil.setDateFormatToDisplay("$dayOfMonth-${month + 1}-$year"))

                    date1=MethodsUtil.setDateDisplay(dateToSet.get().toString())
                    Log.e("DayName", "===>>>$dayOfMonth")
                    Log.e("monthName", "===>>>$month+1")
                    Log.e("FinalDate", "==="+date1)
                }

        val dpDialog = DatePickerDialog(
                context,
                datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        )

        dpDialog.datePicker.minDate = cal.timeInMillis
        dpDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dpDialog.show()

    }

    fun EditProfileApi(){
        var jsonObject=JSONObject()
        jsonObject.put("","")
        jsonObject.put("","")
        jsonObject.put("","")
        jsonObject.put("","")
        jsonObject.put("","")
        jsonObject.put("","")
    }

    fun onclickChangePAss(){
        MethodsUtil.loadFragment(context,ChangePasswordFrag())
    }
}