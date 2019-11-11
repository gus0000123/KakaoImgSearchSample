package com.hyun.android.kakaoimgsearchsample.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.hyun.android.kakaoimgsearchsample.BuildConfig
import com.hyun.android.kakaoimgsearchsample.utils.extend.globalContext

object MyPref {
    val TAG = javaClass.simpleName
    private val pref: SharedPreferences

    private const val FILE_NAME = "${BuildConfig.APPLICATION_ID}Pref"

    const val DEF_STRING = ""
    const val DEF_BOOLEAN = false
    const val DEF_INT = 0
    const val DEF_LONG = 0L

    init {
        pref = globalContext!!.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }


    fun getString(key: String) = pref.getString(key, DEF_STRING)
    fun getInt(key: String) = pref.getInt(key, DEF_INT)
    fun getLong(key: String) = pref.getLong(key, DEF_LONG)
    fun getBoolean(key: String) = pref.getBoolean(key, DEF_BOOLEAN)
    fun getBoolean(key: String, default: Boolean? = null) = pref.getBoolean(key, default ?: DEF_BOOLEAN)


    fun setString(key: String, value: String) {
        pref.edit {
            putString(key, value)
        }
    }

    fun setInt(key: String, value: Int) {
        pref.edit {
            putInt(key, value)
        }
    }

    fun setLong(key: String, value: Long) {
        pref.edit {
            putLong(key, value)
        }

    }

    fun setBoolean(key: String, value: Boolean) {
        pref.edit {
            putBoolean(key, value)
        }

    }


    fun clear() {
        pref.edit {
            clear()
        }
    }


}