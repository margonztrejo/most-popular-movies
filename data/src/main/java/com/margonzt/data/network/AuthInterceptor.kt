package com.margonzt.data.network

import android.util.Log
import com.margonzt.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        val newRequest = oldRequest.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.MOVIES_TOKEN}")
            .build()

        val t1 = System.nanoTime()
        Log.i(
            "REQUEST",
            "Sending request ${newRequest.url()} on ${chain.connection()}" +
                    "\n${newRequest.headers()}"
        )
        Log.v("BODY","\n\nREQUEST BODY BEGIN\n${newRequest.body().toString()}\nREQUEST BODY END")


        val response = chain.proceed(newRequest)

        val responseBodyString = response.peekBody(Long.MAX_VALUE).string()
        Log.v("RESPONSE","RESPONSE BODY BEGIN:\n${responseBodyString}\nRESPONSE BODY END")

        return response
    }

    private fun bodyToString(request: Request): String? {
        return try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()!!.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: Exception) {
            "did not work"
        }
    }
}