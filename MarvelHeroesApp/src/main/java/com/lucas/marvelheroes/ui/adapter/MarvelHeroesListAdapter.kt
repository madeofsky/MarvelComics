package com.lucas.marvelheroes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.ui.viewholder.MarvelComicsListViewHolder

class MarvelHeroesListAdapter(
    private val comics: List<Comic>,
    private val listener: MarvelComicsListViewHolder.OnMarvelHeroCardClickListener
) : RecyclerView.Adapter<MarvelComicsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelComicsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelComicsListViewHolder(inflater, parent, listener)
    }

    override fun onBindViewHolder(holder: MarvelComicsListViewHolder, position: Int) {
        val comics = comics[position]
        holder.bind(comics)
    }

    override fun getItemCount(): Int = comics.size

}