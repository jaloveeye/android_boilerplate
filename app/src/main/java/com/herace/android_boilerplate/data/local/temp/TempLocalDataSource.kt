package com.herace.android_boilerplate.data.local.temp

import androidx.lifecycle.LiveData
import com.herace.android_boilerplate.data.repository.temp.TempDataSource
import io.reactivex.Flowable
import android.content.Context

class TempLocalDataSource(private val tempDatabase: TempDatabase): TempDataSource {

    private val tempDao: TempDao = tempDatabase.getDao()

    override fun setAccessToken(accessToken: String, context: Context) {

    }

    override fun getDatas(id: Int): Flowable<TempEntity> {
        return tempDao.getDataById(id).toFlowable()
    }

    fun insert(data : TempEntity) {
        return tempDao.insert(data)
    }

}