package com.example.haggerplanet.views.editprofile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.haggerplanet.databinding.FragmentProfileDetailBinding
import com.example.haggerplanet.utils.MarshmelloPermissions
import com.example.haggerplanet.views.home.Home
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.fragment_profile_detail.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.io.File

class EditProfile:Fragment() {
    var AUDIO_PERMISSION_CODE=121

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
        Home.mainBinding.itemLayout.tvLang.visibility=View.GONE
        Home.mainBinding.itemLayout.ivLogo.visibility=View.GONE
    }

    @RequiresApi(Build.VERSION_CODES.M)
     fun checkPermission() {

        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context!!,
                        Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                    arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), AUDIO_PERMISSION_CODE
            )
        }else{

            editProfileVM.openImageCropper()
//            employeeListAdapter.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK) {
                    val resultUri: Uri = result.uri


                    editProfileVM.userImage=resultUri.path!!
                    editProfileVM.imageUri.set(resultUri)
                    editProfileVM.isImageSet = true

                    editProfileVM.file = File(resultUri.path)

                    Log.e("MyImageis", "image" + editProfileVM.file)

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    val error = result.error
                }
            }

        }
    }
}