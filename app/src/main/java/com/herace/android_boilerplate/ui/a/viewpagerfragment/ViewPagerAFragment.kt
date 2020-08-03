package com.herace.android_boilerplate.ui.a.viewpagerfragment

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.databinding.BR
import com.herace.android_boilerplate.R
import com.herace.android_boilerplate.data.local.fragmentA.FragmentAEntity
import com.herace.android_boilerplate.data.local.viewPagerA.ViewPagerAEntity
import com.herace.android_boilerplate.databinding.FragmentABinding
import com.herace.android_boilerplate.ui.a.FragmentAViewModel
import com.herace.android_boilerplate.ui.base.BaseFragment
import com.herace.android_boilerplate.util.GenericAdapter
import com.herace.android_boilerplate.util.InfiniteScrollListener
import com.herace.android_boilerplate.util.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_view_pager_a.*
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPagerAFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPagerAFragment : BaseFragment<FragmentABinding, FragmentAViewModel>(), GenericAdapter.OnListItemViewClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val fragmentAViewModel: FragmentAViewModel by viewModel()

    private val mContext = this

    private val genericAdapter = GenericAdapter<ViewPagerAEntity>(R.layout.item_a_fragment)

    private var infiniteScrollListener: InfiniteScrollListener? = null

    private val min_loadMore_interval: Long = 200
    private var mlastLoadMoreTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        println("Fragment A")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewPagerAFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewPagerAFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_view_pager_a
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): FragmentAViewModel {
        return fragmentAViewModel
    }

    override fun setUp() {
        initBinding()
        initObservers()
    }

    override fun onResume() {
        super.onResume()

        fragmentAViewModel.loadList()
    }

    override fun onClick(view: View, position: Int, viewName: String, data: Any) {
        val entity = data as ViewPagerAEntity

        showToast("onClick ${entity.text}")
    }

    private fun initBinding() {

        val layout = LinearLayoutManager(activity)

        infiniteScrollListener = InfiniteScrollListener( { fetchMoreData() }, layout)

        temp_recycler.run {
            setHasFixedSize(true)
            layoutManager = layout
            adapter = genericAdapter
            addItemDecoration(SpaceItemDecoration(requireContext(), 15.0f))
            addOnScrollListener(infiniteScrollListener!!)
        }

        genericAdapter.setOnListItemViewClickListener(this)
    }

    private fun initObservers() {
        with (fragmentAViewModel) {
            loading.visibility = View.INVISIBLE

            getList().observe(viewLifecycleOwner, Observer {
                genericAdapter.addItems(it)
            })
        }
    }

    private fun fetchMoreData() {
        val currentTime = SystemClock.uptimeMillis()
        val elapsedTime = currentTime - mlastLoadMoreTime
        mlastLoadMoreTime = currentTime

        if (elapsedTime > min_loadMore_interval) {
            loading.visibility = View.VISIBLE
            fragmentAViewModel.loadListNextPage()
        }
    }

}