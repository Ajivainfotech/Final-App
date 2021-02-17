package com.example.haggerplanet.utils

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.haggerplanet.R
import java.text.SimpleDateFormat
import java.util.*

object MethodsUtil {

    fun loadFragment(context: Context, fragment: Fragment) {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.frameHome, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun removeFragment(context: Context, fragment: Fragment) {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.frameHome, fragment)
            .commitAllowingStateLoss()
    }

    fun addFragment(context: Context, fragment: Fragment) {
        (context as FragmentActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.frameHome, fragment)
                .addToBackStack(null)
            .commitAllowingStateLoss()
    }



    fun hideSoftKeyboard(activity: Activity) {

        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        Log.e("hideKeyboard", "inside")
    }

    var preHtml = "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "body {\n" +
            "    font-family: 'GothamLight';font-size: 14px; color: black\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n"+
            "<p align=justify style=font-family:GothamLight>"


    var postHtml = "</p>\n" +
            "</body>\n" +
            "</html>"

    fun setDateFormatToDisplay(input: String): String {
        var outputDate = ""

        try {

            var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
            var date = simpleDateFormat.parse(input)

            var simpleDateFormat1 = SimpleDateFormat("dd-MM-yyyy")
            outputDate = simpleDateFormat1.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return outputDate
    }

    fun setDateDisplay(input: String): String {
        var outputDate = ""

        try {

            var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
            var date = simpleDateFormat.parse(input)

            var simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd")
            outputDate = simpleDateFormat1.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return outputDate
    }

    fun convertLocalTimeToUTC(input: String):String{

        var outputDate = ""

        try {

            var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
            var date = simpleDateFormat.parse(input)

            var simpleDateFormat1 = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            simpleDateFormat1.timeZone = TimeZone.getTimeZone("UTC")
            outputDate = simpleDateFormat1.format(date)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return outputDate
    }


    fun getMilliseconds(input: String): Long {

        var output: Long = 0

        try {

            var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            var date = simpleDateFormat.parse(input)
            output = date.time


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return output
    }
    fun getMillisecondsFormat(input: String): Long {

        var output: Long = 0

        try {
            var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            var date = simpleDateFormat.parse(input)
            output = date.time


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return output
    }

    fun getNameOfDay(year: Int, dayOfYear: Int): String? {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.DAY_OF_YEAR] = dayOfYear
        val days = arrayOf(
            "Sun",
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri",
            "Sat"
        )
        val dayIndex = calendar[Calendar.DAY_OF_WEEK]
        return days[dayIndex - 1]
    }
    fun getMonthShortName(monthNumber: Int): String? {
        var monthName = ""
        if (monthNumber >= 0 && monthNumber < 12) try {
            val calendar = Calendar.getInstance()
            calendar[Calendar.MONTH] = monthNumber
            val simpleDateFormat = SimpleDateFormat("MMM")
            //simpleDateFormat.setCalendar(calendar);
            monthName = simpleDateFormat.format(calendar.time)
        } catch (e: java.lang.Exception) {
            e?.printStackTrace()
        }
        return monthName
    }

    fun getDateName(date:String):String{

        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = inFormat.parse(date)
        val outFormat = SimpleDateFormat("EEE, MMM d")
        val goal: String = outFormat.format(date)

        return goal
    }

    fun getDateNamewithYear(date:String):String{

        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = inFormat.parse(date)
        val outFormat = SimpleDateFormat("EEE, d MMM yyyy")
        val goal: String = outFormat.format(date)

        return goal
    }

    fun getTimeFromUTC(input: String): String{

        var output = ""
        try {

            val sdf =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val date = sdf.parse(input)
            sdf.timeZone = TimeZone.getDefault()
            output = sdf.format(date)

        }catch (e:Exception){

            e.printStackTrace()
        }

        return output
    }
    fun showToast(context: Context,message:String){

        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }
}