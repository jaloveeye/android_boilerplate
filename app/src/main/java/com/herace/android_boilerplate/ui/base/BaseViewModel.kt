package com.herace.android_boilerplate.ui.base

import android.app.Application
import android.graphics.Color
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.lang.Exception
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    private var navigator : WeakReference<N>? = null

    val errorLiveData = MediatorLiveData<String>()

    private val compositeDisposable = CompositeDisposable()
    val mIsLoading = ObservableField<Boolean>(false)

    protected open fun onMessage(msg: String) { errorLiveData.value = msg }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun addCompositeDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator() : N? {
        return navigator?.get()
    }
}