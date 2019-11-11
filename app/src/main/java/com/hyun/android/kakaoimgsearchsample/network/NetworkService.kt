package com.hyun.android.kakaoimgsearchsample.network

import com.hyun.android.kakaoimgsearchsample.model.ResKakaoImage
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Network Service Maker
 */
interface NetworkService {
    @GET("v2/search/image")
    fun getImages(@Query("query") query: String): Observable<Response<ResKakaoImage>>

}