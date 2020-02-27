package com.herace.android_boilerplate.data.remote.temp

import com.herace.android_boilerplate.api.ApiService
import com.herace.android_boilerplate.data.local.temp.TempEntity
import com.herace.android_boilerplate.data.repository.temp.TempDataSource
import io.reactivex.Flowable
import java.text.SimpleDateFormat
import java.util.*

class TempRemoteDataSource(): TempDataSource {

    override fun setAccessToken(accessToken: String) {
        ApiService.setAccessToken(accessToken)
    }

    override fun getDatas(id: Int): Flowable<TempEntity> {
        return ApiService.requestDatas()
            .map {

                val datas = it.resultData!!

                TempEntity(
                    datas.id,
                    datas.title,
                    datas.count
                )
            }
    }
}