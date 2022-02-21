package com.lucas.marvelheroes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.ui.viewholder.MarvelHeroesListViewHolder

class MarvelHeroesListAdapter(
    private val comics: List<Comic>,
    private val listener: MarvelHeroesListViewHolder.OnMarvelHeroCardClickListener
) : RecyclerView.Adapter<MarvelHeroesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelHeroesListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelHeroesListViewHolder(inflater, parent, listener)
    }

    override fun onBindViewHolder(holder: MarvelHeroesListViewHolder, position: Int) {
        val comics = comics[position]
        holder.bind(comics)
    }

    override fun getItemCount(): Int = comics.size

}