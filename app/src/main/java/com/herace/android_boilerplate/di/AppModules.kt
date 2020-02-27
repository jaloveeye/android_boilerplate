package com.herace.android_boilerplate.di

import com.herace.android_boilerplate.data.local.fragmentA.FragmentADatabase
import com.herace.android_boilerplate.data.local.fragmentA.FragmentALocalDataSource
import com.herace.android_boilerplate.data.local.temp.TempDatabase
import com.herace.android_boilerplate.data.local.temp.TempLocalDataSource
import com.herace.android_boilerplate.data.remote.fragmentA.FragmentARemoteDataSource
import com.herace.android_boilerplate.data.remote.temp.TempRemoteDataSource
import com.herace.android_boilerplate.data.repository.fragmentA.FragmentARepository
import com.herace.android_boilerplate.data.repository.temp.TempRepository
import com.herace.android_boilerplate.ui.a.FragmentAViewModel
import com.herace.android_boilerplate.ui.temp.TempViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TempViewModel(get(), get())
    }

    viewModel {
        FragmentAViewModel(get(), get())
    }
}

val repositoryModule = module {
    single {
        TempRepository(get(), get())
    }

    single {
        FragmentARepository(get(), get())
    }
}

val dataSourceModule = module {
    single {
        TempRemoteDataSource()
    }
    single {
        TempLocalDataSource(get())
    }

    single {
        FragmentARemoteDataSource()
    }
    single {
        FragmentALocalDataSource(get())
    }
}

val databaseModule = module {
    single {
        TempDatabase.getInstance(androidApplication())
    }

    single {
        FragmentADatabase.getInstance(androidApplication())
    }
}

val appModules = listOf(viewModelModule, repositoryModule, dataSourceModule, databaseModule)