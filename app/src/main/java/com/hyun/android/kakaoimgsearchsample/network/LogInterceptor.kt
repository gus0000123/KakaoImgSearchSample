package com.hyun.android.kakaoimgsearchsample.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer

import java.io.IOException

/**
 * Network Log Message 출력
 */
internal class LogInterceptor : Interceptor {
    private var TAG = javaClass.simpleName
    private var STRING_DIVIDER_CNT = 3950
    private val REQUEST_LOGSTRING_WITHHEADER = " \n" +
            "----------------------------------------------------------------------------------\n" +
            "ReqMethod : %s\n" +
            "ReqUrl : %s\n" +
            "ReqBody : %s\n" +
            "ReqHeader : \n%s\n" +
            "----------------------------------------------------------------------------------\n" +
            "ResHeader : \n%s\n" +
            "ResBody : %s\n" +
            "----------------------------------------------------------------------------------\n "

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
                .build()

        val response = chain.proceed(request)
        val resHeader = response.headers().toString()
        val bodyString = response.body()!!.string()

        var networkLog = String.format(
                REQUEST_LOGSTRING_WITHHEADER,
                request.method(),
                request.url(),
                bodyToString(request),
                request.headers().toString(),
                resHeader,
                bodyString
        )

        //Log 중간에 짤리지 않도록 함.
        try {
            while (networkLog.isNotEmpty()) {
                if (networkLog.length > STRING_DIVIDER_CNT) {
                    Log.d(TAG, networkLog.substring(0, STRING_DIVIDER_CNT))
                    networkLog = " \n" + networkLog.substring(STRING_DIVIDER_CNT)
                } else {
                    Log.d(TAG, networkLog)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return response.newBuilder().body(ResponseBody.create(response.body()!!.contentType(), bodyString)).build()
    }

    /**
     * 리스폰스바디의 값 가져오기
     *
     * @param request 가져와야할 리퀘스트
     * @return 바디 값
     */
    private fun bodyToString(request: Request): String {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()!!.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        } catch (e: NullPointerException) {
            return "did not have body"
        }

    }
}