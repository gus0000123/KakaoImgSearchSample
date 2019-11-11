package com.hyun.android.kakaoimgsearchsample

import android.app.Application
import android.content.Context
import com.hyun.android.kakaoimgsearchsample.utils.extend.globalContext


class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        globalContext = this
    }
}
