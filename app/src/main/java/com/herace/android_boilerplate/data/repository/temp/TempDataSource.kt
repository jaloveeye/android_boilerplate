package com.herace.android_boilerplate.data.repository.temp

import com.herace.android_boilerplate.data.local.temp.TempEntity
import io.reactivex.Flowable

interface TempDataSource {
    fun setAccessToken(accessToken: String)

    fun getDatas(id: Int): Flowable<TempEntity>
}