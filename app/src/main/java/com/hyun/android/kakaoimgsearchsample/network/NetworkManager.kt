package com.hyun.android.kakaoimgsearchsample.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private var instance: Retrofit? = null
    private val BASE_URL = "https://dapi.kakao.com/"
    private const val DEFAULT_TIMEOUT = 60L

    fun getInstance(): Retrofit {
        if (instance == null) {
            var client = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .addInterceptor(HeaderBuilderInterceptor())
                .addInterceptor(LogInterceptor())
                .build()

            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        return instance!!
    }


    fun <T> startService(service: Observable<T>, networkSubscriber: NetworkObserver<T>) {
        service.subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(networkSubscriber as Observer<in T>)
    }

    fun <T> startService(service: Observable<T>) = service.blockingSingle()

}