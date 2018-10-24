package com.nerolink.tools.network

import okhttp3.Interceptor
import okhttp3.Response
import android.util.Log

class LoggerInterceptor : Interceptor {

    val TAG = "from logger interceptor:\n"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.method() == "GET") {
            Log.e(TAG, """
                method:GET
                url:${request.url()}
            """.trimIndent())
        } else if (request.method() == "POST") {
            Log.e(TAG, """
                method:POST
                url:${request.url()}
                body:${request.body()}
            """.trimIndent())
        }
        return chain.proceed(request)
    }
}