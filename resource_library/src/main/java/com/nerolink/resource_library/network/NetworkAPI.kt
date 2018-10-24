package com.nerolink.resource_library.network

import com.nerolink.resource_library.model.ResultLogin
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import com.nerolink.resource_library.constant.WEBConstant


interface NetworkAPI {

    @GET("{path}")
    fun getLoginData(@Path("path") path: String, @QueryMap params: Map<String, String>): Observable<ResultLogin>

}