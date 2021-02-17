package com.example.haggerplanet.retrofit

interface RetrofitResponse {

    fun onResponse(requestCode: Int, response: String, responseCode: Int)

    fun onError(requestCode: Int,msg:String)
}