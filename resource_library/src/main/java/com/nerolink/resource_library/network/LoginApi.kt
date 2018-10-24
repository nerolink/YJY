package com.nerolink.resource_library.network

import com.nerolink.resource_library.model.ResultLogin
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface LoginApi {

//    @POST("{path}")
//    @FormUrlEncoded
//    fun <T> postData(@Path("path") path: String, @FieldMap params: Map<String, String>): Call<T>
//
//    @GET("path")
//    fun <T> getData(@Path("path") path: String, @QueryMap params: Map<String, String>): Call<T>


    @POST("{path}")
    @FormUrlEncoded
    fun  postLoginData(@Path("path") path: String, @FieldMap params: Map<String, String>):Observable<ResultLogin>

    @GET("{path}")
    fun  getLoginData(@Path("path") path: String, @QueryMap params: Map<String, String>): Observable<ResultLogin>
}