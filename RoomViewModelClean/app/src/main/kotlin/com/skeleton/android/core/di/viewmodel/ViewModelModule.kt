package com.skeleton.android.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.skeleton.android.features.events.AddEventViewModel
import com.skeleton.android.features.events.GetEventsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // =============================================================================================
    @Binds
    @IntoMap
    @ViewModelKey(GetEventsViewModel::class)
    abstract fun bindsEventsViewModel(getEventsViewModel: GetEventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEventViewModel::class)
    abstract fun bindsAddEventViewModel(addEventViewModel: AddEventViewModel): ViewModel
}