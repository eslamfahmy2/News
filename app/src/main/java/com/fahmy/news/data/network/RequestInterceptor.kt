package com.fahmy.news.data.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain
            .request().url.newBuilder()
            .addQueryParameter("apiKey", "eed8c595b6d34546b50a7ba474c7a727")
            .build()

        val newRequest = chain
            .request()
            .newBuilder()
            .url(original)
            .build()
        return chain.proceed(newRequest)
    }
}
