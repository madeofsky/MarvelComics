package com.lucas.marvelheroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.presentation.MarvelHeroesListEvent
import com.lucas.marvelheroes.presentation.MarvelHeroesListViewModel
import com.lucas.marvelheroes.ui.adapter.MarvelHeroesListAdapter
import com.lucas.marvelheroes.ui.viewholder.MarvelHeroesListViewHolder
import com.lucas.marvelheroesactivity.databinding.MarvelHeroesListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelHeroesListFragment : Fragment() {

    private lateinit var binding: MarvelHeroesListFragmentBinding

    private val viewModel: MarvelHeroesListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MarvelHeroesListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
        observeViewModel()
    }

    private fun initViews() {

    }

    override fun onResume() {
        super.onResume()
        binding.marvelHeroesListShimmer.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.marvelHeroesListShimmer.stopShimmer()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { listEvent ->
                when (listEvent) {
                    is MarvelHeroesListEvent.Loading -> {
                        setupHeroesListShimmer(true)
                    }
                    is MarvelHeroesListEvent.Failure -> {
                        setupHeroesListShimmer(false)
                        Toast.makeText(requireContext(), listEvent.message, Toast.LENGTH_LONG).show()
                    }
                    is MarvelHeroesListEvent.Success -> {
                        setupHeroesListShimmer(false)
                        initAdapter(listEvent.comicsList)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initAdapter(comicList: List<Comic>) {
        val marvelHeroesAdapter = MarvelHeroesListAdapter(comicList, object :  MarvelHeroesListViewHolder.OnMarvelHeroCardClickListener {
            override fun onMarvelHeroCardClick(comic: Comic, itemPosition: Int) {
                viewModel.onMarvelHeroCardSelect(comic)
            }
        })

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL

        binding.marvelHeroesRecyclerView.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = marvelHeroesAdapter
        }
    }

    private fun setupHeroesListShimmer(isVisible: Boolean) {
        binding.marvelHeroesListShimmer.apply {
            if (isVisible) {
                this.visibility = View.VISIBLE
            } else {
                this.stopShimmer()
                this.visibility = View.GONE
            }
        }
    }
}