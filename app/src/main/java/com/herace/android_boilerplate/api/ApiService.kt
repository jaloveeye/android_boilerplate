package com.herace.android_boilerplate.api

import com.herace.android_boilerplate.data.remote.fragmentA.FragmentAResponse
import com.herace.android_boilerplate.data.remote.temp.TempData
import com.herace.android_boilerplate.data.remote.temp.TempResponse
import io.reactivex.Flowable
import retrofit2.http.*

class ApiService {

    interface ApiServiceImpl {
        companion object {
            const val PRE_URL = "/api/v1"
        }

        @GET("${PRE_URL}/datas")
        fun requestGet() : Flowable<TempResponse>

        @POST("${PRE_URL}/datas")
        fun requestPost(@Body data: TempData) : Flowable<TempResponse>

        @DELETE("${PRE_URL}/datas")
        fun requestDelete(@Query("reason") reason: String) : Flowable<TempResponse>

        @GET("${PRE_URL}/datas/{id}")
        fun getData2Id(@Path("id") id : String ) :Flowable<TempResponse>

        @PUT("${PRE_URL}/datas")
        fun requestPut(@Body data: TempData): Flowable<TempResponse>

        @GET("${PRE_URL}/datas/{id}")
        fun requestFragmentADataById(@Path("id") id : String ) :Flowable<FragmentAResponse>
    }

    companion object {
        private lateinit var retrofitApi: ApiServiceImpl

        fun setAccessToken(token: String) {
            retrofitApi = RetrofitCreatorApi.create(ApiServiceImpl::class.java, token)
        }

        fun requestDatas() : Flowable<TempResponse> {
            return retrofitApi.requestGet()
        }

        fun requestFragmentADataById(id: String) : Flowable<FragmentAResponse> {
            return retrofitApi.requestFragmentADataById(id)
        }
    }
}