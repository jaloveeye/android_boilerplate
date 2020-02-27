package com.herace.android_boilerplate.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import timber.log.Timber
import java.lang.Exception

abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel<*>> : AppCompatActivity(), BaseFragment.CallBack {

    private lateinit var mViewDataBinding: T

    protected open val errorObserver = Observer<String> {it?.let { processError(it) } }
    protected open fun processError(error: String) = Toast.makeText(this, error, Toast.LENGTH_SHORT).show()

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    /**
     * Binding 을 위한 함수
     */
    abstract fun getBindingVariable(): Int

    abstract fun setUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setUp()
    }

    private fun performDataBinding() {

        try {
            mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
            mViewDataBinding.lifecycleOwner = this
            mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
            mViewDataBinding.executePendingBindings()
        } catch (e: Exception) {
            Timber.e(e.message.toString())
        }
    }

    fun getViewDataBinding() : T {
        return mViewDataBinding
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }
}