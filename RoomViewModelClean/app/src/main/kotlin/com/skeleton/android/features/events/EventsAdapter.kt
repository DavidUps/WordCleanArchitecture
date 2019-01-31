package com.skeleton.android.features.events

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.skeleton.android.R
import com.skeleton.android.core.extension.inflate
import com.skeleton.android.core.extension.loadFromUrl
import kotlinx.android.synthetic.main.view_event.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class EventsAdapter
@Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER: Int = 0
        private const val TYPE_ITEM: Int = 1
        private const val TYPE_FOOTER: Int = 2
    }

    internal var collection: List<EventView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (EventView) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> EventItemViewHolder(parent.inflate(R.layout.row_event_item))
            TYPE_HEADER -> EventHeaderViewHolder(parent.inflate(R.layout.row_event_header))
            else -> return EventFooterViewHolder(parent.inflate(R.layout.row_event_footer))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isPositionHeader(position) -> EventsAdapter.TYPE_HEADER
            isPositionFooter(position) -> EventsAdapter.TYPE_FOOTER
            else -> EventsAdapter.TYPE_ITEM
        }
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == EventsAdapter.TYPE_HEADER
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == collection.size + 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is EventItemViewHolder -> viewHolder.bind(collection[position - 1], clickListener)
            is EventHeaderViewHolder -> viewHolder.bind()
            is EventFooterViewHolder -> viewHolder.bind()
        }
    }

    override fun getItemCount() = collection.size + 2

    class EventItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(eventView: EventView, clickListener: (EventView) -> Unit) {
            if (!eventView.images.isNullOrEmpty()) {
                itemView.iv_eventPoster.loadFromUrl(eventView.images[0].bucket_url)
            }
            itemView.setOnClickListener {
                clickListener(eventView)
            }
        }
    }

    class EventHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    class EventFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }
}