package com.lucas.marvelheroes.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucas.marvelheroesapp.R
import com.lucas.marvelheroes.data.models.Comic
import kotlinx.android.synthetic.main.marvel_comic_item.view.*

class MarvelComicsListViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: OnMarvelHeroCardClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.marvel_comic_item, parent, false)) {

    fun bind(comic: Comic) {
        itemView.apply {
            textViewComicTitle.text = comic.title
            textViewComicDescription.text = comic.description

            Glide.with(context)
                .load("${comic.thumbnail?.path}/standard_amazing.${comic.thumbnail?.extension}")
                .into(imageViewComicsImage)
        }
    }


    interface OnMarvelHeroCardClickListener {
        fun onMarvelHeroCardClick(comic: Comic, itemPosition: Int)
    }
}