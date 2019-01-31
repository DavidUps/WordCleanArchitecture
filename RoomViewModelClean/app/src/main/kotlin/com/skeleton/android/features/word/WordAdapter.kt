package com.skeleton.android.features.word

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skeleton.android.R
import com.skeleton.android.core.extension.inflate
import com.skeleton.android.core.extension.loadFromUrl
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlinx.android.synthetic.main.view_event.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class WordAdapter

@Inject constructor(): RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    internal var collection: List<WordView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (WordView) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.recyclerview_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position])
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(wordView: WordView) {
            if (!wordView.word.isNullOrEmpty()) {
                itemView.tvWord.text = (wordView.word)
            }
        }
    }
}
