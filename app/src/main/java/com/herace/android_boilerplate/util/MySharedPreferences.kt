package com.herace.android_boilerplate.util

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {


    val PREFS_FILENAME = "prefs"
    val PREF_KEY_CONNECT = "connectServer"
    val PREF_KEY_USERID = "userId"
    val PREF_KEY_ACCESSTOKEN = "accessToken"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var connectServer: String
        get() = prefs.getString(PREF_KEY_CONNECT, "")!!
        set(value) = prefs.edit().putString(PREF_KEY_CONNECT, value).apply()

    var userId : Int
        get() = prefs.getInt(PREF_KEY_USERID, -1)
        set(value) = prefs.edit().putInt(PREF_KEY_USERID, value).apply()

    var accessToken : String
        get() = prefs.getString(PREF_KEY_ACCESSTOKEN, "")!!
        set(value) = prefs.edit().putString(PREF_KEY_ACCESSTOKEN, value).apply()
}