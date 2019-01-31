package com.skeleton.android.core.navigation

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.skeleton.android.features.events.EventView
import com.skeleton.android.features.events.EventsDetailsFragment
import com.skeleton.android.features.word.WordFragment
import com.skeleton.android.features.word.WordView
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    // Activities ==================================================================================
    fun showInitial(context: Context) {
        showMainActivity(context)
    }

    private fun showMainActivity(context: Context) = context.startActivity(MainActivity.callingIntent(context))

    // =============================================================================================

    // Fragments ===================================================================================
    fun showEventDetailsFragment(activity: FragmentActivity, event: EventView) {
        (activity as MainActivity).replaceFragment(EventsDetailsFragment.newInstance(event), "EventDetailsFragment")
    }

    fun showWordFragment(activity: FragmentActivity, word: WordView){
        (activity as MainActivity).replaceFragment(WordFragment.newInstance(word), "WordFragment")
    }
}