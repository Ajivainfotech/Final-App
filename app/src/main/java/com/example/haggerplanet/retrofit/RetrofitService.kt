package com.example.haggerplanet.retrofit

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.haggerplanet.R
import com.example.haggerplanet.utils.CustomAlerts
import com.example.haggerplanet.utils.PrefferenceFile
import com.example.haggerplanet.utils.WebApiKeys
import com.example.haggerplanet.views.login.Login
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import kotlinx.coroutines.*
import okhttp3.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.cert.X509Certificate
import java.util.ArrayList
import java.util.HashMap

import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class RetrofitService {

    private var mContext: Context? = null
    private var mUrl: String = ""
    private var mRequestCode: Int = 0
    internal lateinit var retrofit: Retrofit
    private var mValue: Int = 0
    private var mCall: Call<ResponseBody>? = null
    private var mResponse: RetrofitResponse? = null
    private var mPart2: MultipartBody.Part? = null
    private var mPart3: MultipartBody.Part? = null
    private var mPart1: MultipartBody.Part? = null
    private var mMap: HashMap<String, RequestBody>? = null
    private var mJsonObject: JSONObject?= null
    // private var progressDialog: ProgressDialog? = null
    private var mFiles: ArrayList<MultipartBody.Part>? = null
    private var mFiles2: ArrayList<MultipartBody.Part>? = null
    internal lateinit var dialog: AlertDialog
    private var progressDialog: Dialog? = null

    constructor(context: Context, response: RetrofitResponse, url: String, requestCode: Int, value: Int) {
        //For Get Url
        mContext = context
        mResponse = response
        mUrl = url
        mRequestCode = requestCode
        mValue = value
    }


    constructor(context: Context, response: RetrofitResponse, url: String,
                requestCode: Int, value: Int, map: HashMap<String, RequestBody>, part1: MultipartBody.Part) {
        mContext = context
        mResponse = response
        mUrl = url
        mRequestCode = requestCode
        mValue = value
        mMap = map
        mPart1 = part1
    }

    constructor(context: Context, response: RetrofitResponse, url: String,
                requestCode: Int, value: Int, map: HashMap<String, RequestBody>, part1: MultipartBody.Part, part2: MultipartBody.Part) {
        mContext = context
        mResponse = response
        mUrl = url
        mRequestCode = requestCode
        mValue = value
        mMap = map
        mPart1 = part1
        mPart2 = part2

    }


    constructor(context: Context, response: RetrofitResponse, url: String,
                requestCode: Int, value: Int, map: HashMap<String, RequestBody>, part1: MultipartBody.Part, part2: MultipartBody.Part, part3: MultipartBody.Part) {
        mContext = context
        mResponse = response
        mUrl = url
        mRequestCode = requestCode
        mValue = value
        mMap = map
        mPart1 = part1
        mPart2 = part2
        mPart3 = part3
    }


    constructor(context: Context, response: RetrofitResponse, url: String,
                requestCode: Int, value: Int, map: HashMap<String, RequestBody>, files1: ArrayList<MultipartBody.Part>, files2: ArrayList<MultipartBody.Part>
    ) {
        mContext = context
        mResponse = response
        mUrl = url
        mRequestCode = requestCode
        mValue = value
        mMap = map
        mFiles = files1
        mFiles2=files2
    }

    constructor(context: Context, response: RetrofitResponse, url: String, requestcode: Int, value: Int, postParam: JSONObject)
    //For Post Url  --- pass 3
    {
        mContext = context
        mResponse = response
        mUrl = url
        mRequestCode = requestcode
        mValue = value
        mJsonObject = postParam
    }


    //For multipart multiple files
    constructor(context: Context, response: RetrofitResponse, url: String, requestCode: Int, value: Int, map: HashMap<String,
            RequestBody>, files: ArrayList<MultipartBody.Part>
    ) {
        mContext = context
        mResponse = response
        mUrl = url
        mRequestCode = requestCode
        mValue = value
        mMap = map
        mFiles = files
    }

    fun getUnsafeOkHttpClient(): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains

            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {

                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

            })



            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }



            val okHttpClient = OkHttpClient.Builder()

            okHttpClient.addInterceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Authorization","Basic eWVudGVtOnByMG1AdCFjQHkzbnQzbWMwclA=")
                    .header("Cache-Control","no-cache")
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }






            return builder
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES).build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }


    fun callService(dialogFlag: Boolean, token: String) {
        try {

            progressDialog = Dialog(mContext!!)
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)
            progressDialog!!.setContentView(R.layout.progress_dialog)
            progressDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            //  progressDialog!!.show()


            if (dialogFlag) {
                progressDialog!!.show()
            }

            if (token.equals("")) {
                val okHttpClient = OkHttpClient.Builder()
                    /*.readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)*/

                    .readTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(WebApiKeys.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }

            else if (!token.equals("")  ) {
                val okHttpClient = OkHttpClient.Builder()
                /*.readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)*/
                okHttpClient.addInterceptor { chain ->
                    val request =
                        chain.request().newBuilder().addHeader("X-Requested-With", token)
                            .build()
                    chain.proceed(request)
                }

                    .callTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(WebApiKeys.BASE_URL)
                    .client(okHttpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            } else if (!token.equals("") ){
                val httpClient = OkHttpClient.Builder()

                httpClient.addInterceptor { chain ->
                    val request =
                        chain.request().newBuilder().addHeader("X-Requested-With", token)
                        .build()
                    chain.proceed(request)
                }
                retrofit = Retrofit.Builder()
                    .baseUrl(WebApiKeys.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }else{

                val httpClient = OkHttpClient.Builder()

                httpClient.addInterceptor { chain ->
                    val request =
                        chain.request().newBuilder().addHeader("X-Requested-With", token).
                       build()
                    chain.proceed(request)
                }
                retrofit = Retrofit.Builder()
                    .baseUrl(WebApiKeys.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()

            }




            val retrofitApi = retrofit.create(RetrofitApi::class.java)

            when (mValue)
            {
                1 -> {
                    mCall = retrofitApi.callGet(mUrl)
                    Log.e("GetTypeurl ", mUrl)
                }
                2 -> {
                    mCall = retrofitApi.callMultipart(mUrl, mMap, mPart1, mPart2)
                    Log.e("url ", mUrl)
                    Log.e("params>>", mMap!!.toString())
                }
                3 -> {
                    Log.e("url ", WebApiKeys.BASE_URL+mUrl)
                    Log.e("params ", mJsonObject.toString())
                    mCall = retrofitApi.callPost(mUrl, RequestBody.create(MediaType.parse("application/json"), mJsonObject.toString()))
                }
                4 -> {
                    Log.e("url ", mUrl)
                    Log.e("params ", mMap!!.toString())
                    mCall = retrofitApi.callMultipartList(mUrl, mMap!!, mFiles)
                }
                5 -> {
                    Log.e("url>>>", mUrl)
                    Log.e("params>>>>", mMap!!.toString())
                    mCall = retrofitApi.callMultipartOne(mUrl, mMap!!, mPart1)
                }
                6 -> {
                    Log.e("url ", mUrl)
                    Log.e("params ", mMap!!.toString())
                    mCall = retrofitApi.callMultipartList2(mUrl, mMap!!, mFiles,mFiles2)
                }
               /* 7 -> {
                    Log.e("url ", mUrl)
                    Log.e("params ", mMap!!.toString())
                    mCall = retrofitApi.callMultipartThree(mUrl, mMap!!, mPart1,mPart2,mPart3)
                }*/
            }

            if(mCall==null) {
                Log.e("mcall","nullllll")
            }
            else
            {
                Log.e("mcall","Nottnullllll")

            }

            val coRoutineExceptionHandler = CoroutineExceptionHandler { _, t ->
                t.printStackTrace()

                CoroutineScope(Dispatchers.Main).launch {
                    if (progressDialog!!.isShowing)
                    {
                        progressDialog!!.cancel()
                    }
//                    requestProcessor.onException(t.message)

                    if (t.message.equals("Unable to resolve host"))
                    {
                        Log.e("Server Error", "Unable to resolve host")

                    }
                    else
                    { //timeout
                        Log.e("Server Error", "TimeOut")
                        CustomAlerts.commonAlert(mContext!!,"TimeOut")
                    }

                }
            }


            mCall!!.enqueue(object : Callback<ResponseBody>
            {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>)
                {

                    CoroutineScope(Dispatchers.IO + coRoutineExceptionHandler).launch {

                        CoroutineScope(Dispatchers.Main).launch{
                            Log.e("resCodeIs", "====${response.code()}")

                            try
                            {
                                if (progressDialog!!.isShowing)
                                {
                                    progressDialog!!.cancel()
                                }
                            }
                            catch (e: Exception)
                            {
                            }

                            if (response.isSuccessful)
                            {
                                try
                                {
                                    val res = response.body()!!.string().toString()
                                    Log.e("Response","${response.code() } "+ res)

                                    mResponse!!.onResponse(mRequestCode, res, response.code())
                                }
                                catch (e: IOException)
                                {
                                    e.printStackTrace()
                                }
                            }
                            else
                            {
                                val res = response.errorBody()!!.string()
                                Log.e("RetrofitService", "onResponse: $res " + response.code())

                                Log.e("Response_unsuccessful",res)

                                if (res.isNotEmpty() && res.startsWith("{"))
                                {
                                    if (res == "Unauthorized")
                                    {
                                        CustomAlerts.commonAlert(mContext!!, "Session Expired! Please login again.")

                                        PrefferenceFile.removeAll(mContext!!)

                                        mContext!!.startActivity(Intent(mContext, Login::class.java))
                                    }
                                    else
                                    {
                                        val jsonObject = JSONObject(res)
                                        when
                                        {
                                            jsonObject.has("errors") ->
                                            {
                                                val errorObj = jsonObject.getJSONObject("errors")
                                                val message = errorObj.getJSONObject("message")
//                                                makeToast(mContext!!, message.getString("en"))

                                                if (message.getString("en") == "Bad Token")
                                                {
                                                    CustomAlerts.commonAlert(mContext!!, "Session Expired! Please login again.")

                                                    PrefferenceFile.removeAll(mContext!!)

                                                    mContext!!.startActivity(Intent(mContext, Login::class.java))
                                                }
                                                else
                                                {
                                                    mResponse!!.onError(mRequestCode,message.getString("en"))
                                                }

                                            }
                                            jsonObject.has("message") ->
                                            {
                                                val message = jsonObject.getString("message")

                                                if (message == "Bad Token")
                                                {
                                                    CustomAlerts.commonAlert(mContext!!, "Session Expired! Please login again.")

                                                    PrefferenceFile.removeAll(mContext!!)

                                                    mContext!!.startActivity(Intent(mContext, Login::class.java))
                                                }
                                                else
                                                {
                                                    mResponse!!.onError(mRequestCode,message)
                                                }

                                            }
                                            else ->
                                            {
                                                CustomAlerts.commonAlert(mContext!!,"Server Error")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable)
                {
                    try {
                        if (progressDialog!!.isShowing)
                        {
                            progressDialog!!.cancel()
                        }
                    } catch (e: Exception) {
                    }

                    Log.e("onFailure ", "onFailure")
                    alertOnTimeOut(mCall, this, mContext!!.resources.getString(R.string.connection_timeout))
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun alertOnTimeOut(call: Call<ResponseBody>?, callback: Callback<ResponseBody>, message: String) {

        try {
            val alertDialog = AlertDialog.Builder(mContext)
            alertDialog.setMessage(message)

            alertDialog.setPositiveButton(mContext!!.resources.getString(R.string.retry)) { dialog, which ->
                progressDialog!!.show()

                call!!.clone().enqueue(callback)
            }

            alertDialog.setNegativeButton(mContext!!.resources.getString(R.string.cancell)) { dialog, which -> dialog.dismiss() }
            dialog = alertDialog.create()
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}