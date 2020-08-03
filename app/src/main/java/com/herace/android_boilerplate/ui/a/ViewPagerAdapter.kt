package com.herace.android_boilerplate.ui.a

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.herace.android_boilerplate.ui.a.viewpagerfragment.*

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    var list: List<Fragment> = listOf()


    init {
        list = listOf<Fragment>(
            ViewPagerAFragment(),
            ViewPagerBFragment(),
            ViewPagerCFragment(),
            ViewPagerDFragment(),
            ViewPagerEFragment()
        )
    }


    override fun createFragment(position: Int): Fragment {
        return list.get(position)
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}