package com.herace.android_boilerplate.data.repository.temp

import com.herace.android_boilerplate.data.local.temp.TempEntity
import io.reactivex.Flowable
import android.content.Context

interface TempDataSource {
    fun setAccessToken(accessToken: String, context: Context)

    fun getDatas(id: Int): Flowable<TempEntity>
}