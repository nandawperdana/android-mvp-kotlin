package com.nandawperdana.kotlinmvp.util


import com.nandawperdana.kotlinmvp.AndroidApplication
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/**
 * Created by nandawperdana.
 */
class CacheInterceptor : Interceptor {

    var body = ""
        private set

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.method() == "GET") {
            if (AndroidApplication.getInstance.isConnected()) {
                request = request.newBuilder().removeHeader("pragma").removeHeader("Cache-Control").header("Cache-Control", "only-if-cached").build()
            } else {
                request = request.newBuilder().removeHeader("pragma").removeHeader("Cache-Control").header("Cache-control", "max-stale=2419200").build()
            }
        }
        val response = chain.proceed(request)
        body = response.body()!!.string()
        return response.newBuilder().body(ResponseBody.create(response.body()!!.contentType(), body)).build()
    }
}
