package com.herace.android_boilerplate.ui.temp

import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herace.android_boilerplate.BR
import com.herace.android_boilerplate.R
import com.herace.android_boilerplate.databinding.ActivityTempBinding
import com.herace.android_boilerplate.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_temp.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TempActivity : BaseActivity<ActivityTempBinding, TempViewModel>(), TempNavigator {

    private val mViewModel: TempViewModel by viewModel()

    private val mContext = this

    override fun getLayoutId(): Int {
        return R.layout.activity_temp
    }

    override fun getViewModel(): TempViewModel {
        mViewModel.setNavigator(this)
        return mViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun setUp() {
        val host = supportFragmentManager.findFragmentById(R.id.navHostfragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigation, host.navController)

        val id: Int = 0

        mViewModel.setAccessToken("access_token", mContext)
        mViewModel.getDatas(id)

        mViewModel.data.observe(this, Observer {

        })
    }
}
