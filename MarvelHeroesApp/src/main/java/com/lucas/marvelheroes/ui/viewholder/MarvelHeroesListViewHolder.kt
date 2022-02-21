package com.lucas.marvelheroes.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroesactivity.R
import kotlinx.android.synthetic.main.marvel_heroes_comic_item.view.*

class MarvelHeroesListViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: OnMarvelHeroCardClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.marvel_heroes_comic_item, parent, false)) {

    fun bind(comic: Comic) {
        itemView.apply {
            textViewHeroTitle.text = comic.title
            textViewHeroDescription.text = comic.description

            Glide.with(context)
                .load(comic.thumbnail)
                .into(imageViewHeroImage)
        }
    }


    interface OnMarvelHeroCardClickListener {
        fun onMarvelHeroCardClick(comic: Comic, itemPosition: Int)
    }
}