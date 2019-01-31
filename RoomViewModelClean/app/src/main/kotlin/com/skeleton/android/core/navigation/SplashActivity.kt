package com.skeleton.android.core.navigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.skeleton.android.AndroidApplication
import com.skeleton.android.core.di.ApplicationComponent
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    @Inject internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showInitial(this)
    }
}
