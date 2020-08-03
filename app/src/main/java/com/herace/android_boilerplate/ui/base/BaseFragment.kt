package com.herace.android_boilerplate.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import splitties.toast.toast
import timber.log.Timber
import java.lang.Exception

abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel<*>> : Fragment() {

    private lateinit var mViewDataBinding: T
    private var mActivity: BaseActivity<*, *>? = null

    protected open val errorObserver = Observer<String> { it?.let { processError(it) } }
    protected open fun processError(error: String) = Toast.makeText(this@BaseFragment.context, error, Toast.LENGTH_SHORT).show()

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getViewModel(): V

    abstract fun setUp()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is BaseActivity<*, *>) {

            try {
                mActivity = context
                mActivity?.onFragmentAttached()
            } catch (e: Exception) {
                Timber.e(e.message.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // Fragment Option Menu 사용
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
            mViewDataBinding.lifecycleOwner = this
            mViewDataBinding.executePendingBindings()

            setUp()
        } catch (e: Exception) {
            Timber.e(e.message.toString())
        }
    }

    fun getBaseActivity() : BaseActivity<*, *>? {
        return mActivity
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    fun showToast(msg: String?) {

        if (!msg.isNullOrEmpty()) {
            println(msg)
            toast(msg)
        }
    }

    fun getBinding() = mViewDataBinding
}