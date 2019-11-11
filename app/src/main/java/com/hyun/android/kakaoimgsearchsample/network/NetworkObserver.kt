package com.hyun.android.kakaoimgsearchsample.network

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response

abstract class NetworkObserver<T> : Observer<T> {
    val myTAG = javaClass.simpleName
    val UNKNWON = -1

    override fun onNext(t: T) {
        var response = t as Response<T>
        if (response.isSuccessful) {
            onResponse(t)
        } else
            onFailure(UNKNWON, Throwable("Unknown Network Error"))

    }


    override fun onError(e: Throwable) {
        if (e != null) {
            e.printStackTrace()
            onFailure(UNKNWON, e)
        }
    }

    override fun onComplete() {
        Log.d(myTAG, "onComplete")
    }

    override fun onSubscribe(d: Disposable) {
        Log.d(myTAG, "Disposable :${d.toString()}")
    }

    abstract fun onFailure(code: Int, t: Throwable)
    abstract fun onResponse(response: T)
}