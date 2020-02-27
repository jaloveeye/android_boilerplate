package com.herace.android_boilerplate.api

import com.herace.android_boilerplate.BuildConfig
import com.herace.android_boilerplate.MyApplication
import com.herace.android_boilerplate.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class RetrofitCreatorApi {
    companion object {

        val API_BASE_URL_TEST = "https://testurl"

        lateinit var TOKEN: String

        private fun defaultRetrofit(token: String): Retrofit {

            var URL : String
            val connectServer = MyApplication.prefs.connectServer
            if (connectServer.isNullOrEmpty() || connectServer.equals(Constants.CONNECT_SERVER_REAL)) URL = Constants.API_BASE_URL_REAL
            else URL = Constants.API_BASE_URL_TEST

            Timber.d("Server is ${connectServer}, URL is ${URL}")

            TOKEN = "Bearer ${token}"

            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .client(createOkHttpClient())
                .build()
        }

        fun <T> create(service: Class<T>, token: String): T {
            return defaultRetrofit(token).create(service)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
//                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }

            val headerSettingInterceptor: HeaderSettingInterceptor = HeaderSettingInterceptor(TOKEN)

            return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(headerSettingInterceptor)
                .build()
        }
    }


    private class HeaderSettingInterceptor(token: String): Interceptor {

        val accessToken = token

        override fun intercept(chain: Interceptor.Chain): Response {
            val chainRequest = chain.request()

            val request = chainRequest.newBuilder().apply{
                addHeader("Authorization", accessToken)
            }.build()

            return chain.proceed(request)
        }
    }
}