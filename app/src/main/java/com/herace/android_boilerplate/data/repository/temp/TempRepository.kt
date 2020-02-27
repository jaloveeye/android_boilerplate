package com.herace.android_boilerplate.data.repository.temp

import com.herace.android_boilerplate.data.local.temp.TempEntity
import com.herace.android_boilerplate.data.local.temp.TempLocalDataSource
import com.herace.android_boilerplate.data.remote.temp.TempRemoteDataSource
import io.reactivex.Flowable
import timber.log.Timber

class TempRepository(
    private val localDataSource: TempLocalDataSource,
    private val remoteDataSource: TempRemoteDataSource
) : TempDataSource {

    private var cached = mutableMapOf<Int, TempEntity>()
    private var cacheIsDirty = false



    override fun setAccessToken(accessToken: String) {
        remoteDataSource.setAccessToken(accessToken)
    }

    override fun getDatas(id: Int): Flowable<TempEntity> {
        if (cached[id] != null && !cacheIsDirty) {
            Timber.d("get cached")
            return Flowable.just(cached[id])
        }

        val remoteData = getAndSaveRemoteData(id)

        return if (cacheIsDirty) remoteData
        else {
            val localData = getAndCacheLocalData(id)

            Flowable.concat(localData, remoteData)
                .onErrorResumeNext(Flowable.empty())
        }
    }

    private fun getAndSaveRemoteData(id: Int): Flowable<TempEntity> {
        return remoteDataSource.getDatas(id)
            .doOnNext { it ->
                localDataSource.insert(it)
                cached[id] = it
                Timber.d("get remote")
            }.doOnComplete {
                cacheIsDirty = false
            }
    }

    private fun getAndCacheLocalData(id:Int): Flowable<TempEntity> {
        return localDataSource.getDatas(id)
            .doOnNext { it ->
                cached[id] = it
                Timber.d("get local")
            }
    }

    fun refresh() {
        cacheIsDirty = true
    }
}