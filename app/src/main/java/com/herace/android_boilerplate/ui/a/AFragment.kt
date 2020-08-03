package com.herace.android_boilerplate.ui.a


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.herace.android_boilerplate.BR
import com.herace.android_boilerplate.MyApplication

import com.herace.android_boilerplate.R
import com.herace.android_boilerplate.databinding.FragmentABinding
import com.herace.android_boilerplate.ui.base.BaseFragment
import com.herace.android_boilerplate.util.SingleScrollDirectionEnforcer
import kotlinx.android.synthetic.main.fragment_a.*
import org.koin.androidx.viewmodel.ext.android.viewModel


val ViewPager2.recyclerView: RecyclerView
    get() {
        return this[0] as RecyclerView
    }

private fun RecyclerView.enforceSingleScrollDirection() {
    val enforcer = SingleScrollDirectionEnforcer()
    addOnItemTouchListener(enforcer)
    addOnScrollListener(enforcer)
}


/**
 * A simple [Fragment] subclass.
 */
class AFragment : BaseFragment<FragmentABinding, FragmentAViewModel>() {

    private val fragmentAViewModel : FragmentAViewModel by viewModel()
//    private val recyclerAdapter = FragmentARecyclerAdapter()

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
//        FragmentARecyclerAdapter.setContext(this@AFragment.context!!)

//        recyclerAdapter.setOnItemClickListener { _, fragmentAEntity, _ ->
//
//        }

//        fragmentARecycler.adapter = recyclerAdapter
//
//        fragmentARecycler.addItemDecoration(ItemDecoration(this@AFragment, 3, 0.0F, 24.0F, false))

//        fragmentAViewModel.setAccessToken(MyApplication.prefs.accessToken)
//        fragmentAViewModel.getUserInfo(MyApplication.prefs.userId)

//        fragmentAViewModel.mutableLiveData.observe(this , Observer {
//            fragmentAViewModel.updateData(it)
//        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpager2.adapter = ViewPagerAdapter(this@AFragment)

        viewpager2.offscreenPageLimit = 3

        /**
         * reduces drag sensitivity
         */
        val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(viewpager2) as RecyclerView

        val sensitivityValue = 4
        val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int
        touchSlopField.set(recyclerView, touchSlop * sensitivityValue)

        viewpager2.recyclerView.enforceSingleScrollDirection()

        val tabArray = resources.getStringArray(R.array.tab_string)

        TabLayoutMediator(tab_home, viewpager2, false,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = tabArray[position]
            }).attach()
    }

}
