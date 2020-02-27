package com.herace.android_boilerplate.ui.temp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.herace.android_boilerplate.data.local.temp.TempEntity
import com.herace.android_boilerplate.data.repository.temp.TempRepository
import com.herace.android_boilerplate.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class TempViewModel(application: Application, private val repository: TempRepository): BaseViewModel<TempNavigator>(application) {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    private val _data = MutableLiveData<TempEntity>()
    val data : LiveData<TempEntity>
        get() = _data

    fun setAccessToken(accessToken: String) {
        repository.setAccessToken(accessToken)
    }

    private val compositeDisposable = CompositeDisposable()

    fun getDatas(id: Int) {
        compositeDisposable.add(
            repository.getDatas(id)
                .map {
                        it -> it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .doAfterTerminate { }
                .subscribe(
                    {
                        _data.postValue(it)
                    },
                    {
                        Timber.e("${it.message}")
                    }
                )
        )
    }
}