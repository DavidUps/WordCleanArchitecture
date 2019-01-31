package com.skeleton.android.core.platform

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.skeleton.android.R.id
import com.skeleton.android.core.extension.inTransaction

/**
 * Base Activity class with helper methods for handling fragment transactions and back button events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {


    fun addFragment(savedInstanceState: Bundle?, tag: String) =
            savedInstanceState ?: supportFragmentManager.inTransaction {
                add(id.fragmentContainer, fragment(), tag)
            }

    fun replaceFragment(fragment: BaseFragment, tag: String) =
            supportFragmentManager.inTransaction {
                replace(id.fragmentContainer, fragment, tag)
                addToBackStack(null)
            }

    abstract fun fragment(): BaseFragment
}