package com.herace.android_boilerplate.ui.a


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.herace.android_boilerplate.BR
import com.herace.android_boilerplate.MyApplication

import com.herace.android_boilerplate.R
import com.herace.android_boilerplate.databinding.FragmentABinding
import com.herace.android_boilerplate.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_a.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class AFragment : BaseFragment<FragmentABinding, FragmentAViewModel>() {

    private val fragmentAViewModel : FragmentAViewModel by viewModel()
    private val recyclerAdapter = FragmentARecyclerAdapter()

    override fun getLayoutId(): Int {
        return R.layout.fragment_a
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): FragmentAViewModel {
        return fragmentAViewModel
    }

    override fun setUp() {
        FragmentARecyclerAdapter.setContext(this@AFragment.context!!)

        recyclerAdapter.setOnItemClickListener { _, fragmentAEntity, _ ->

        }

        fragmentARecycler.adapter = recyclerAdapter

        fragmentARecycler.addItemDecoration(ItemDecoration(this@AFragment, 3, 0.0F, 24.0F, false))

//        fragmentAViewModel.setAccessToken(MyApplication.prefs.accessToken)
//        fragmentAViewModel.getUserInfo(MyApplication.prefs.userId)

        fragmentAViewModel.mutableLiveData.observe(this , Observer {
            fragmentAViewModel.updateData(it)
        })
    }

}
