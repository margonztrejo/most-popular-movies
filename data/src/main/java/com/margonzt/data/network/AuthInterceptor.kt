package com.margonzt.data.network

import com.margonzt.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        val newUrl = oldRequest.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.MOVIES_API_KEY)
            .build()
        val newRequest = oldRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}