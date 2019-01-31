package com.skeleton.android.core.di

import com.skeleton.android.AndroidApplication
import com.skeleton.android.core.di.viewmodel.ViewModelModule
import com.skeleton.android.core.navigation.SplashActivity
import com.skeleton.android.features.events.EventsDetailsFragment
import com.skeleton.android.features.events.EventsFragment
import com.skeleton.android.features.word.WordFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(splashActivity: SplashActivity)

    fun inject(eventsFragment: EventsFragment)
    fun inject(eventDetailsFragment: EventsDetailsFragment)

    fun inject(wordFragment: WordFragment)

}