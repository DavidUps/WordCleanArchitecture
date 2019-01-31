package com.skeleton.android.core.utils

import android.content.Context
import android.content.res.Configuration
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceChecker
@Inject constructor(){

    open fun isATablet(context: Context):Boolean{
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.COLOR_MODE_HDR_MASK
    }
}