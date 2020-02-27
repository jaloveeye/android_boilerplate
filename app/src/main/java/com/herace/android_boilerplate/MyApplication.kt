package com.herace.android_boilerplate

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.herace.android_boilerplate.util.AppLogger
import com.herace.android_boilerplate.util.MySharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.herace.android_boilerplate.di.appModules

class MyApplication : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        AppLogger.init()

        prefs = MySharedPreferences(applicationContext)

        AndroidNetworking.initialize(applicationContext)

        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }

        // start Koin!
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // modules
            modules(appModules)
        }

        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    companion object {
        var instance: MyApplication? = null

        lateinit var prefs: MySharedPreferences
    }
}