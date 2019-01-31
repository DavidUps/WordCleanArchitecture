package com.skeleton.android.features.events

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.skeleton.android.R
import com.skeleton.android.core.extension.loadFromUrl
import com.skeleton.android.core.navigation.Navigator
import com.skeleton.android.core.platform.BaseFragment
import com.skeleton.android.features.consumable.ConsumableAdapter
import kotlinx.android.synthetic.main.fragment_event_details.*
import kotlinx.android.synthetic.main.view_event_detail.*
import javax.inject.Inject


class EventsDetailsFragment : BaseFragment() {

    private var event: EventView? = null

    companion object {
        fun newInstance(event: EventView): EventsDetailsFragment {
            val fragment = EventsDetailsFragment()
            val args = Bundle()
            args.putParcelable("event", event)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var consumablesAdapter: ConsumableAdapter

    override fun layoutId() = R.layout.fragment_event_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments
        if (arguments != null) {
            event = arguments.getParcelable("event")
            initLayout()
            loadConsumables()
        }
    }

    private fun initLayout() {
        initPoster()
        initConsumables()
    }

    private fun initPoster() {
        if (event!=null) {
            val images = event?.images
            if (!images.isNullOrEmpty()) {
                iv_eventPoster.loadFromUrl(images.first().bucket_url)
            }
        }
    }

    private fun initConsumables() {
        rv_consumables_list.layoutManager = LinearLayoutManager(activity)
        rv_consumables_list.adapter = consumablesAdapter
        consumablesAdapter.clickListener = {
            Log.d("ConsumableClick", it.name)
        }
    }

    private fun loadConsumables() {
        consumablesAdapter.collection = event?.consumables.orEmpty()
    }
}