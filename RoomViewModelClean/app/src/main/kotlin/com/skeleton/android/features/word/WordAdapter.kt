package com.skeleton.android.features.word

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skeleton.android.R
import com.skeleton.android.features.events.EventView
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlin.properties.Delegates

class WordAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()

    internal var collection: List<WordView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView = itemView.tvWord
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordAdapter.WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, p0, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(p0: WordAdapter.WordViewHolder, p1: Int) {
        val current = words[p1]
        p0.wordItemView.text = current.word
    }

    internal fun setWords(words: List<Word>){
        this.words = words
        notifyDataSetChanged()
    }
}