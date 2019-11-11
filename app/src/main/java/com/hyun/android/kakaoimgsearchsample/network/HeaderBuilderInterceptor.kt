package com.hyun.android.kakaoimgsearchsample.network

import com.hyun.android.kakaoimgsearchsample.R
import com.hyun.android.kakaoimgsearchsample.utils.extend.globalContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeaderBuilderInterceptor : Interceptor {
    private var TAG = javaClass.simpleName
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var builder: Request.Builder = original.newBuilder()
        builder = builder.header("Accept", "application/sp.app-v1+json")
        builder = builder.header("Content-Type", "application/json;charset=UTF-8")
        builder = builder.header("Authorization", "KakaoAK ${globalContext?.getString(R.string.rest_api_key)}")

        builder = builder.method(original.method(), original.body())

        return chain.proceed(builder.build())
    }
}