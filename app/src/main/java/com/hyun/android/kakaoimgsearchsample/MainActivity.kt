package com.hyun.android.kakaoimgsearchsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.hyun.android.kakaoimgsearchsample.model.ResKakaoImage
import com.hyun.android.kakaoimgsearchsample.network.NetworkManager
import com.hyun.android.kakaoimgsearchsample.network.NetworkObserver
import com.hyun.android.kakaoimgsearchsample.network.NetworkService
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var service = NetworkManager.getInstance().create(NetworkService::class.java).getImages("설현adsf")
        NetworkManager.startService(service, object : NetworkObserver<Response<ResKakaoImage>>() {
            override fun onResponse(response: Response<ResKakaoImage>) {
                Log.d(TAG, "response::${response.body()}")
            }

            override fun onFailure(code: Int, t: Throwable) {
            }

        })
    }
}
