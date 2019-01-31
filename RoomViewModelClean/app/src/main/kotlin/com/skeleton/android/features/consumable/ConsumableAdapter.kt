package com.skeleton.android.features.consumable

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.skeleton.android.R
import com.skeleton.android.core.extension.inflate
import kotlinx.android.synthetic.main.row_consumable_item.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ConsumableAdapter
@Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER: Int = 0
        private const val TYPE_ITEM: Int = 1
        private const val TYPE_FOOTER: Int = 2
    }

    internal var collection: List<ConsumableView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (ConsumableView) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> ConsumableItemViewHolder(parent.inflate(R.layout.row_consumable_item))
            TYPE_HEADER -> ConsumableHeaderViewHolder(parent.inflate(R.layout.row_consumable_header))
            else -> return ConsumableFooterViewHolder(parent.inflate(R.layout.row_consumable_footer))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isPositionHeader(position) -> TYPE_HEADER
            isPositionFooter(position) -> TYPE_FOOTER
            else -> TYPE_ITEM
        }
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == TYPE_HEADER
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == collection.size + 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is ConsumableItemViewHolder -> viewHolder.bind(collection[position - 1], clickListener)
            is ConsumableHeaderViewHolder -> viewHolder.bind()
            is ConsumableFooterViewHolder -> viewHolder.bind()
        }
    }

    override fun getItemCount() = collection.size + 2

    class ConsumableItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(consumable: ConsumableView, clickListener: (ConsumableView) -> Unit) {
            itemView.tv_name.text = consumable.name
            itemView.setOnClickListener {
                clickListener(consumable)
            }
        }
    }

    class ConsumableHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    class ConsumableFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }
}