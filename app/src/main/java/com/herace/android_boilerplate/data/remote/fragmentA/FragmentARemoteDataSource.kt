package com.herace.android_boilerplate.data.remote.fragmentA

import com.herace.android_boilerplate.api.ApiService
import com.herace.android_boilerplate.data.local.fragmentA.FragmentAEntity
import io.reactivex.Flowable

class FragmentARemoteDataSource {

    fun requestDataById(id: Int) : Flowable<List<FragmentAEntity>> {
        return ApiService.requestFragmentADataById(id.toString())
            .map {
                val response = it.resultData?.data

                response?.map {
                    FragmentAEntity(
                        it.id,
                        it.title,
                        it.content,
                        it.displayOrder,
                        it.warning,
                        it.inspection
                    )
                }
            }
    }
}