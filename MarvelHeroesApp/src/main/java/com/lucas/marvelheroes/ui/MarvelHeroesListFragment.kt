package com.lucas.marvelheroes.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucas.marvelheroes.presentation.MarvelHeroesListViewModel
import com.lucas.marvelheroesactivity.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelHeroesListFragment : Fragment() {

    companion object {
        fun newInstance() = MarvelHeroesListFragment()
    }

    private lateinit var viewModel: MarvelHeroesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.marvel_heroes_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MarvelHeroesListViewModel::class.java)
    }

}