package com.skeleton.android.core.navigation

import com.skeleton.android.core.functional.DialogCallback


interface PopUpDelegator {

    fun showErrorWithRetry(title: String, message: String, positiveText: String,
                           negativeText: String, callback: DialogCallback)
}