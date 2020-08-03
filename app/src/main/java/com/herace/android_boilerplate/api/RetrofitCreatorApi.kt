package com.herace.android_boilerplate.api

import android.content.Context
import com.herace.android_boilerplate.BuildConfig
import com.herace.android_boilerplate.MyApplication
import com.herace.android_boilerplate.util.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class RetrofitCreatorApi {
    companion object {

        val API_BASE_URL_TEST = "https://testurl"

        lateinit var TOKEN: String

        private fun defaultRetrofit(token: String, context: Context): Retrofit {

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
                .client(createOkHttpClient(context))
                .build()
        }

        fun <T> create(service: Class<T>, token: String, context: Context): T {
            return defaultRetrofit(token, context).create(service)
        }

        private fun createOkHttpClient(context: Context): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val headerSettingInterceptor: HeaderSettingInterceptor = HeaderSettingInterceptor(TOKEN)

//            val responseInterceptor = ResponseInterceptor()

            return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(headerSettingInterceptor)
                .addInterceptor(NoConnectionInterceptor(context))
//                .addInterceptor(responseInterceptor)
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

//    private class ResponseInterceptor() : Interceptor {
//
//        override fun intercept(chain: Interceptor.Chain): Response {
//
//            val logoutCode = 403
//
//            val expireAt = MyApplication.prefs.accessToken_expireAt
//
//            val locale = Locale.getDefault()
//            val parser =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
//            parser.timeZone = TimeZone.getTimeZone("UTC")
//
//            val calendar = Calendar.getInstance()
//
//            try {
//                calendar.time = parser.parse(expireAt)
//            } catch (e: Exception) {
//                println(e.message.toString())
//            }
//
//            if (System.currentTimeMillis() >= calendar.timeInMillis) {
//                val getNewAccessToken = GlobalScope.async {
//                    val refreshToken = RequestNewAccessToken(MyApplication.prefs.refreshToken)
//                    ApiService.requestNewAccessToken(refreshToken)
//                }
//
//                val newAccessToken = runBlocking {
//                    getNewAccessToken.await()
//                }
//
//                when (newAccessToken.resultCode) {
//                    1000 -> {
//                        TOKEN = "Bearer ${newAccessToken.resultData?.userToken!!}"
//
//                        MyApplication.prefs.accessToken = newAccessToken.resultData.userToken!!
//                        MyApplication.prefs.accessToken_expireAt = newAccessToken.resultData.userTokenExpireAt!!
//
//                        val newRequest = chain.request().newBuilder().removeHeader("Authorization").addHeader("Authorization", TOKEN).build()
//                        return chain.proceed(newRequest)
//                    }
//                    9204 -> {
//                        return Response.Builder().code(logoutCode).build()
//                    }
//                    else -> {
//                        return Response.Builder().code(logoutCode).build()
//                    }
//                }
//            } else {
//                val response = chain.proceed(chain.request())
//
//                /**
//                 * AccessToken 만료
//                 */
//                if (response.code == 401) {
//
//                    println("response code ${response.code}")
//
//                    val getNewAccessToken = GlobalScope.async {
//                        val refreshToken = RequestNewAccessToken(MyApplication.prefs.refreshToken)
//                        ApiService.requestNewAccessToken(refreshToken)
//                    }
//
//                    val newAccessToken = runBlocking {
//                        getNewAccessToken.await()
//                    }
//
//                    when (newAccessToken.resultCode) {
//                        1000 -> {
//                            TOKEN = "Bearer ${newAccessToken.resultData?.userToken!!}"
//
//                            MyApplication.prefs.accessToken = newAccessToken.resultData.userToken!!
//                            MyApplication.prefs.accessToken_expireAt = newAccessToken.resultData.userTokenExpireAt!!
//
//                            val newRequest = chain.request().newBuilder().removeHeader("Authorization").addHeader("Authorization", TOKEN).build()
//                            return chain.proceed(newRequest)
//                        }
//                        9204 -> {
//                            /**
//                             * refresh token 만료
//                             * 로그 아웃 처리해야 함
//                             */
//                            println("refresh token expired")
//
//                            return response.newBuilder().code(logoutCode).build()
//                        }
//                        else -> {
//                            return response.newBuilder().code(logoutCode).build()
//                        }
//                    }
//                }
//
//                return response
//            }
//        }
//    }
}