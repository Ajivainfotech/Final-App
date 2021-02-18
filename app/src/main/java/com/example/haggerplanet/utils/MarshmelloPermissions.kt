package com.example.haggerplanet.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*


class MarshmelloPermissions(internal var activity: Activity) {

    fun checkPermissionForReadContacts(): Boolean {
        val result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForRecord(): Boolean {
        val result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForReadExternalStorage(): Boolean {
        val result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForWriteExternalStorage(): Boolean {
        val result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForCamera(): Boolean {
        val result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissionForCameraAndMicrophone(): Boolean {
        val resultCamera = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
        val resultMic = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
        return resultCamera == PackageManager.PERMISSION_GRANTED && resultMic == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissionForRecord() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(activity, "Microphone permission needed for recording.Please allow in App SettingsFragment for additional functionality.", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_PERMISSION_REQUEST_CODE)
        }
    }

    fun requestPermissionForReadExternalStorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, "External Storage permission needed. Please allow in App SettingsFragment for additional functionality.", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
        }
    }

    fun requestPermissionForWriteExternalStorage() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //            CustomUtils.snackBarForPermissions(view,"External Storage permission needed",activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE)
        }
    }

    fun requestPermissionForCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            Toast.makeText(activity, "Camera permission needed. Please allow in App SettingsFragment for additional functionality.", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    fun requestPermissionForCameraAndMicrophone() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(activity, "Audio permission needed", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO), CAMERA_MIC_PERMISSION_REQUEST_CODE)
        }
    }

    fun requestPermissionForReadContacts() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(activity, "Read Contacts permission needed. Please allow in App SettingsFragment for additional functionality.", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_CONTACTS), READ_CONTACTS_REQUEST_CODE)
        }
    }

    fun commonPermissions(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            val headWritePermission =
                activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val headReadPermission =
                activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            val headCameraPermission =
                activity.checkSelfPermission(Manifest.permission.CAMERA)
            val permissionList =
                ArrayList<String>()
            if (headWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (headReadPermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (headCameraPermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.CAMERA)
            }
            if (!permissionList.isEmpty()) {
                activity.requestPermissions(permissionList.toTypedArray(), 2)
            } else {
                return true
            }
        } else if (Build.VERSION.SDK_INT < 23) {
            Log.e("Check", " Permissions Granted")
            return true
        }
        return false
    }


    companion object {

        val RECORD_PERMISSION_REQUEST_CODE = 1
        val WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2
        val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 3
        val CAMERA_PERMISSION_REQUEST_CODE = 4
        val READ_CONTACTS_REQUEST_CODE = 5
        val CAMERA_MIC_PERMISSION_REQUEST_CODE = 6
        val RECORD_AUDIO = 7

        //gps location permission
        fun locPermissionCheck(activity: Activity): Boolean {

            if (Build.VERSION.SDK_INT >= 23) {
                val hasReadPermission = activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                val hasWritePermission = activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                val hasNetworkStatePermission = activity.checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)

                val permissionList = ArrayList<String>()

                if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
                }
                if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
                }
                if (hasNetworkStatePermission != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE)
                }
                if (!permissionList.isEmpty()) {
                    //  activity.requestPermissions(permissionList.toTypedArray<String>(), 2)
                } else {
                    return true
                }
            } else if (Build.VERSION.SDK_INT < 23) {
                return true
            }
            return false
        }
    }



}
