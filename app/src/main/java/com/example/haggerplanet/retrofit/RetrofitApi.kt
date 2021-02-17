package com.example.haggerplanet.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList
import java.util.HashMap

interface RetrofitApi {

    @GET
    fun callGet(@Url url: String): Call<ResponseBody>

    @POST
    fun callPost(@Url url: String, @Body body: RequestBody): Call<ResponseBody>

    @FormUrlEncoded
    @POST
    fun callPostHeader(@Url url: String, @Body body: RequestBody): Call<ResponseBody>

    @Multipart
    @POST
    fun callMultipart(@Url url: String?, @PartMap map: HashMap<String, RequestBody>?,
                      @Part part: MultipartBody.Part?, @Part part1: MultipartBody.Part?): Call<ResponseBody>

    @Multipart
    @POST
    fun callMultipartOne(@Url url: String, @PartMap map: HashMap<String, RequestBody>, @Part part: MultipartBody.Part?): Call<ResponseBody>

    @Multipart
    @POST
    fun callMultipartList(@Url url: String, @PartMap map: HashMap<String, RequestBody>,@Part part: ArrayList<MultipartBody.Part>?)
            : Call<ResponseBody>

    @Multipart
    @POST
    fun callMultipartList2(@Url url: String, @PartMap map: HashMap<String, RequestBody>,@Part part1: ArrayList<MultipartBody.Part>?,@Part part2: ArrayList<MultipartBody.Part>?)
            : Call<ResponseBody>

    @POST("charge")
    fun callPayment(@Body body: JSONObject):Call<ResponseBody>



}