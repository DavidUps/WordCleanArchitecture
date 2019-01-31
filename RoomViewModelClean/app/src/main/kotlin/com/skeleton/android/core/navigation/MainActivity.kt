package com.skeleton.android.core.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.skeleton.android.R
import com.skeleton.android.core.functional.DialogCallback
import com.skeleton.android.core.platform.BaseActivity
import com.skeleton.android.core.platform.BaseFragment
import com.skeleton.android.features.word.WordFragment


class MainActivity : BaseActivity(), PopUpDelegator {

    override fun fragment() = WordFragment()

    companion object {
        fun callingIntent(context: Context): Intent {
            val intentToLaunch = Intent(context, MainActivity::class.java)
            intentToLaunch.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intentToLaunch
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        addFragment(savedInstanceState, "wordFragment")
    }

    override fun showErrorWithRetry(title: String, message: String, positiveText: String, negativeText: String,
                                    callback: DialogCallback) {

        // Implementar aqui el dialog con el que quereis mostrar los errores y en función
        // del boton pulsado llamar a callback.onAccept() o callback.onDecline() que lo que hace es
        // delegar al fragment

    }

    override fun onBackPressed() {
        val container = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (container!!.tag.equals("wordFragment")) {
            moveTaskToBack(true)
        } else {
            ((container) as BaseFragment).onBackPressed()
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        val container = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

    }

    // Propaga el onActivityResult al fragment inflado
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val container = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        ((container) as BaseFragment).onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}