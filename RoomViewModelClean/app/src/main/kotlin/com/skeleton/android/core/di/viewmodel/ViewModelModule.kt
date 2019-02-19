package com.skeleton.android.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.skeleton.android.features.people.GetPeopleViewModel
import com.skeleton.android.features.word.AddWordViewModel
import com.skeleton.android.features.word.GetWordViewModel
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
    @ViewModelKey(GetWordViewModel::class)
    abstract fun bindsWordViewModel(getWordViewModel: GetWordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddWordViewModel::class)
    abstract fun bindsAddWordViewModel(addWordViewModel: AddWordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GetPeopleViewModel::class)
    abstract fun bindsGetPeopleViewModel(getPeopleViewModel: GetPeopleViewModel): ViewModel
}