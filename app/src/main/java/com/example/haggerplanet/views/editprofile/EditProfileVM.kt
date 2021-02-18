package com.example.haggerplanet.views.editprofile

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.android.tools.build.jetifier.core.utils.Log
import com.example.haggerplanet.R
import com.example.haggerplanet.retrofit.RetrofitResponse
import com.example.haggerplanet.retrofit.RetrofitService
import com.example.haggerplanet.utils.CommonKeys
import com.example.haggerplanet.utils.MethodsUtil
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.changePasswordFrag.ChangePasswordFrag
import com.example.haggerplanet.views.forgotPassword.ResetPassword
import com.theartofdev.edmodo.cropper.CropImage
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class EditProfileVM(val context: Context,val editProfile: EditProfile):ViewModel() ,RetrofitResponse{

    var imageUri = ObservableField<Uri>()
    var userImage = ""
    var isImageSet = false
    var file: File?= null
    var name= ObservableField("")
    var lastName= ObservableField("")
    var phone= ObservableField("")
    var email= ObservableField("")
    var gender= ObservableField("")
    var dob= ObservableField("")
    var location= ObservableField("")
    var date= ObservableField("")
    var date1=""
    private var hashMap: HashMap<String, RequestBody>? = null

    init {
        name.set(PrefferenceFile.retrieveKey(context,CommonKeys.USER_NAME))
        email.set(PrefferenceFile.retrieveKey(context,CommonKeys.USER_EMAIL))
        phone.set(PrefferenceFile.retrieveKey(context,CommonKeys.USER_PHONE_NUMBER))
        setSpinner()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun onClickPermission() {
        editProfile.checkPermission()
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

                    dob.set(MethodsUtil.setDateDisplay(dateToSet.get().toString()))
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


    fun openImageCropper() {
        android.util.Log.e("inside", "openImageCropper->")
        CropImage.activity().start(context ,editProfile)
    }

    fun editProfileApi(){
        hashMap= HashMap()
        var id=PrefferenceFile.retrieveKey(context,CommonKeys.USER_ID)
       Log.e("editProfileApi", "useriddd->$id")

            if (file!=null){

                val requestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)

                val multiPart =
                        MultipartBody.Part.createFormData("image", file!!.name, requestBody)

                hashMap!!["user_id"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), id.toString())

                hashMap!!["location"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), "Chandigarh")

                hashMap!!["first_name"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), name.get().toString())

                hashMap!!["last_name"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), lastName.get().toString())

                hashMap!!["mobile"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), phone.get().toString())

                hashMap!!["gender"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), gender.get().toString())

                hashMap!!["email"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), email.get().toString())


                hashMap!!["dob"] =
                        RequestBody.create(MediaType.parse("multipart/form-data"), dob.get().toString())

                RetrofitService(
                        context,
                        this,

                        WebApiKeys.EDIT_PROFILE,
                        WebApiKeys.EDIT_PROFILE_REQ,
                        5,
                        hashMap!!,multiPart
                ).callService(
                        true, ""
                )

                android.util.Log.e("hasMapEditProfile","Paramsare"+hashMap!!+"==="+multiPart)

            }else{
            var jsonObject=JSONObject()
            jsonObject.put("user_id",id)
            jsonObject.put("location","Chandigarh")
            jsonObject.put("first_name",name.get().toString())
            jsonObject.put("last_name",lastName.get().toString())
            jsonObject.put("mobile",phone.get().toString())
            jsonObject.put("gender",gender.get().toString())
            jsonObject.put("email",email.get().toString())
            jsonObject.put("dob",dob.get().toString())

            RetrofitService(context,this,WebApiKeys.EDIT_PROFILE,WebApiKeys.EDIT_PROFILE_REQ,3,jsonObject).callService(true,"")

        }

    }

    fun onClickEditProfile(){
        if (validation()){
            editProfileApi()
        }
    }

    fun validation():Boolean{
        when{
            name.get().toString().isNullOrEmpty()->{
                Toast.makeText(context,"Please enter name",Toast.LENGTH_SHORT).show()
                return false
            }
            lastName.get().toString().isNullOrEmpty()->{
                Toast.makeText(context,"Please enter Lastname",Toast.LENGTH_SHORT).show()
                return false
            }
            phone.get().toString().isNullOrEmpty()->{
                Toast.makeText(context,"Please enter mobile number",Toast.LENGTH_SHORT).show()
                return false
            }
            phone.get().toString().length <7 ->{
                Toast.makeText(context,"Please enter valid mobile number",Toast.LENGTH_SHORT).show()
                return false
            }
            gender.get().toString().isNullOrEmpty()->{
                Toast.makeText(context,"Please enter gender",Toast.LENGTH_SHORT).show()
                return false
            }
            email.get().toString().isNullOrEmpty()->{
                Toast.makeText(context,"Please enter email",Toast.LENGTH_SHORT).show()
                return false
            }
            dob.get().toString().isNullOrEmpty()->{
                Toast.makeText(context,"Please enter DOB",Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }
        }
    }


    fun onclickChangePAss(){
        MethodsUtil.loadFragment(context,ChangePasswordFrag())
    }

    override fun onResponse(requestCode: Int, response: String, responseCode: Int) {
        when(requestCode){
            WebApiKeys.EDIT_PROFILE_REQ->{
                try {
                    var jsonObject=JSONObject(response)
                    if (jsonObject.getBoolean("status")){
                        Log.e("EditProfile", "===>>>$jsonObject")
                        Toast.makeText(context,jsonObject.getString("responseMessage"),Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(context,jsonObject.getString("responseMessage"),Toast.LENGTH_SHORT).show()
                    }

                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }

    }

    override fun onError(requestCode: Int, msg: String) {

    }
}