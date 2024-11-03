package com.part3.mediasearch.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().addHeader("Authorization", "KakaoAK ${apiKey}").build()
        return chain.proceed(request)
    }
}