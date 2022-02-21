package com.lucas.marvelheroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroesapp.databinding.MarvelComicsListFragmentBinding
import com.lucas.marvelheroes.presentation.MarvelComicsListEvent
import com.lucas.marvelheroes.presentation.MarvelComicsListViewModel
import com.lucas.marvelheroes.ui.adapter.MarvelHeroesListAdapter
import com.lucas.marvelheroes.ui.viewholder.MarvelComicsListViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelComicsListFragment : Fragment() {

    private lateinit var binding: MarvelComicsListFragmentBinding

    private val viewModel: MarvelComicsListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MarvelComicsListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
//        binding.marvelHeroesListShimmer.startShimmer()
    }

    override fun onPause() {
        super.onPause()
//        binding.marvelHeroesListShimmer.stopShimmer()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.comics.collect { comicsEvent ->
                when (comicsEvent) {
                    is MarvelComicsListEvent.Loading -> {
                        setupComicsListShimmer(true)
                    }
                    is MarvelComicsListEvent.Failure -> {
                        setupComicsListShimmer(false)
                        Toast.makeText(requireContext(), comicsEvent.message, Toast.LENGTH_LONG).show()
                    }
                    is MarvelComicsListEvent.Success -> {
                        setupComicsListShimmer(false)
                        initAdapter(comicsEvent.comicsList)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initAdapter(comicList: List<Comic>) {
        val marvelHeroesAdapter = MarvelHeroesListAdapter(comicList, object :  MarvelComicsListViewHolder.OnMarvelHeroCardClickListener {
            override fun onMarvelHeroCardClick(comic: Comic, itemPosition: Int) {
                viewModel.onMarvelHeroCardSelect(comic)
            }
        })

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL

        binding.marvelComicsRecyclerView.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = marvelHeroesAdapter
        }
    }

    private fun setupComicsListShimmer(isVisible: Boolean) {
//        binding.marvelHeroesListShimmer.apply {
//            if (isVisible) {
//                this.visibility = View.VISIBLE
//            } else {
//                this.stopShimmer()
//                this.visibility = View.GONE
//            }
//        }
    }
}