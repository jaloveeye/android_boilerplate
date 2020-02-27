package com.herace.android_boilerplate.data.repository.fragmentA

import com.herace.android_boilerplate.data.local.fragmentA.FragmentAEntity
import com.herace.android_boilerplate.data.local.fragmentA.FragmentALocalDataSource
import com.herace.android_boilerplate.data.remote.fragmentA.FragmentARemoteDataSource
import io.reactivex.Flowable
import timber.log.Timber

class FragmentARepository (private val localDataSource : FragmentALocalDataSource, private val remoteDataSource: FragmentARemoteDataSource) {

    private var cached = mutableMapOf<Int, List<FragmentAEntity>>()
    private var cacheIsDirty = false

    fun requestDataById(id: Int): Flowable<List<FragmentAEntity>> {
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

    private fun getAndSaveRemoteData(id: Int): Flowable<List<FragmentAEntity>> {
        return remoteDataSource.requestDataById(id)
            .doOnNext { it ->
                localDataSource.insert(it)
                cached[id] = it
                Timber.d("get remote")
            }.doOnComplete {
                cacheIsDirty = false
            }
    }

    private fun getAndCacheLocalData(id:Int): Flowable<List<FragmentAEntity>> {
        return localDataSource.getDataById(id)
            .doOnNext { it ->
                cached[id] = it
                Timber.d("get local")
            }
    }

    fun refresh() {
        cacheIsDirty = true
    }
}