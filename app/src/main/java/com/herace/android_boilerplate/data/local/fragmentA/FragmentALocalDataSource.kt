package com.herace.android_boilerplate.data.local.fragmentA

import io.reactivex.Flowable

class FragmentALocalDataSource(private val dataBase: FragmentADatabase) {
    private val dao : FragmentADao = dataBase.getDao()

    fun getDataById(id: Int) : Flowable<List<FragmentAEntity>> {
        return dao.getDataById(id).toFlowable()
    }

    fun insert(fragmentAEntityList: List<FragmentAEntity>) {
        return dao.insert(fragmentAEntityList)
    }
}