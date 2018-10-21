package com.nerolink.tools.network

import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    @POST("{path}")
    @FormUrlEncoded
    fun <T> postData(@Path("path") path: String, @FieldMap params: Map<String, String>): Call<T>

    @GET("path")
    fun <T> getData(@Path("path") path: String, @QueryMap params: Map<String, String>): Call<T>


}