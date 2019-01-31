package com.skeleton.android.features.events

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.skeleton.android.R
import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.extension.*
import com.skeleton.android.core.functional.DialogCallback
import com.skeleton.android.core.navigation.Navigator
import com.skeleton.android.core.platform.BaseFragment
import com.skeleton.android.features.consumable.Consumable
import com.skeleton.android.features.places.Place
import kotlinx.android.synthetic.main.fragment_events.*
import javax.inject.Inject
import kotlin.random.Random


class EventsFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_events

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var eventsAdapter: EventsAdapter

    private lateinit var getGetEventsViewModel: GetEventsViewModel
    private lateinit var addEventViewModelGet: AddEventViewModel

    private var eventImages = listOf("http://latinosmag.com/wp-content/uploads/2017/11/event-new-years-eve.jpg",
            "https://images.pexels.com/photos/1190298/pexels-photo-1190298.jpeg?auto=compress&cs=tinysrgb&h=350",
            "http://wpc.72c72.betacdn.net/8072C72/lvi-images/sites/default/files/styles/landscape_1020_560/public/nota_periodistica/fiesta_7.jpg",
            "http://www.morrisons2.hu/wp-content/uploads/2016/05/slider_retro2.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        getGetEventsViewModel = viewModel(viewModelFactory) {
            observe(events, ::renderEventsList)
            failure(failure, ::handleFailure)
        }

        addEventViewModelGet = viewModel(viewModelFactory) {
            observe(trigger, ::onEventCreated)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        initListeners()
        loadEvents()
    }

    private fun initListeners() {
        fab_addEvent onClick this::addRandomEvent
    }

    private fun initializeView() {
        eventList.layoutManager = LinearLayoutManager(activity)
        eventList.adapter = eventsAdapter
        eventsAdapter.clickListener = { event ->
            navigator.showEventDetailsFragment(activity!!, event)
        }
    }

    private fun addRandomEvent() {
        showProgress()
        addEventViewModelGet.add(randomEvent())
    }

    private fun randomEvent(): Event {
        val images = listOf(randomEventImage())
        val consumables: MutableList<Consumable> = mutableListOf()
        repeat(10) {
            consumables.add(randomConsumable())
        }
        return Event(Random.nextInt(Integer.MAX_VALUE), Place.empty(), consumables, images)
    }

    private fun randomEventImage(): EventImage {
        val eventImage = EventImage.empty()
        eventImage.bucket_url = eventImages[Random.nextInt(eventImages.size-1)]
        return eventImage
    }

    private fun randomConsumable(): Consumable {
        return Consumable(Random.nextInt(Integer.MAX_VALUE), randomString())
    }

    private fun randomString(): String = ('A'..'z').map { it }.shuffled().subList(0, 10).joinToString("")


    private fun onEventCreated(any: Any?) {
        hideProgress()
        loadEvents()
    }

    private fun loadEvents() {
        emptyView.invisible()
        eventList.visible()
        showProgress()
        getGetEventsViewModel.events()
    }

    private fun renderEventsList(events: List<EventView>?) {
        eventsAdapter.collection = events.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.CustomError -> renderFailure(failure.errorCode, failure.errorMessage)
        }
    }

    private fun renderFailure(errorCode: Int, errorMessage: String?) {
        eventList.invisible()
        emptyView.visible()
        hideProgress()
        showError(errorCode, errorMessage, object : DialogCallback {
            override fun onAccept() {
                loadEvents()
            }

            override fun onDecline() {
                onBackPressed()
            }
        })
    }
}