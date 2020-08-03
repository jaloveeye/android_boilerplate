package com.herace.android_boilerplate.ui.a

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.herace.android_boilerplate.data.local.fragmentA.FragmentAEntity
import com.herace.android_boilerplate.data.local.viewPagerA.ViewPagerAEntity
import com.herace.android_boilerplate.data.repository.fragmentA.FragmentARepository
import com.herace.android_boilerplate.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.ArrayList
import kotlin.system.measureTimeMillis

class FragmentAViewModel(application: Application, private val repository: FragmentARepository) : BaseViewModel<Any>(application) {

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing : LiveData<Boolean>
        get() = _isRefreshing

    val observableList = ObservableArrayList<FragmentAEntity>()
    val mutableLiveData = MutableLiveData<List<FragmentAEntity>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg : LiveData<String>
        get() = _errorMsg

    private val compositeDisposable = CompositeDisposable()

    fun getDataById(id: Int) {
        compositeDisposable.add(
            repository.requestDataById(id)
                .map {
                        it -> it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _isRefreshing.postValue(true) }
                .doAfterTerminate { _isRefreshing.postValue(false) }
                .subscribe(
                    {
                        mutableLiveData.value = filterCategoryList(it.sortedWith(compareBy({ it.displayOrder })) , true)
                    }, {
                        Timber.e("${it.message.toString()}")
                        _errorMsg.value = it.message.toString()
                    }
                )
        )
    }

    fun refreshData() {
        try {
            val elapsed: Long = measureTimeMillis {
                repository.refresh()
                val id: Int = 0
                this.getDataById(id)
            }

            Timber.i("refreshData elapsed time is ${elapsed.toString()} ms")
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
        }
    }

    private fun filterCategoryList(data : List<FragmentAEntity> , status : Boolean): List<FragmentAEntity> {
        var returnValue: List<FragmentAEntity>

        if (!status) {
            returnValue = data.filter { it.displayOrder!! > 12 }
        } else {
            returnValue = data.filter { it.displayOrder!! <= 12 }
        }
        return returnValue.sortedBy { it.inspection == false }
    }

    fun updateData(categoryList: List<FragmentAEntity>) {
        observableList.clear()
        observableList.addAll(categoryList)
    }



    private val list = MutableLiveData<java.util.ArrayList<ViewPagerAEntity>>()
    fun getList() = list
    fun loadList() {
        val localData = mutableListOf<ViewPagerAEntity>()
        for (x in 0 until 100) {
            val viewPagerAEntity = ViewPagerAEntity(x, "텍스트 ${x + 1}")
            localData.add(viewPagerAEntity)
        }

        list.postValue(localData as ArrayList<ViewPagerAEntity>?)
    }

    fun loadListNextPage() {

    }
}