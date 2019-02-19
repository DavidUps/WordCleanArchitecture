package com.skeleton.android.core.di

import com.skeleton.android.AndroidApplication
import com.skeleton.android.core.di.viewmodel.ViewModelModule
import com.skeleton.android.core.navigation.SplashActivity
import com.skeleton.android.features.word.WordFragment
import com.skeleton.android.features.word.AddWordFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(splashActivity: SplashActivity)
    fun inject(wordFragment: WordFragment)
    fun inject(addWordFragment: AddWordFragment)
}