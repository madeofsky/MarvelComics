package com.lucas.marvelheroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lucas.marvelheroes.presentation.MarvelHeroesListEvent
import com.lucas.marvelheroes.presentation.MarvelHeroesListViewModel
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

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { listEvent ->
                when (listEvent) {
                    is MarvelHeroesListEvent.Loading -> TODO()
                    is MarvelHeroesListEvent.Failure -> TODO()
                    is MarvelHeroesListEvent.Success -> TODO()
                    else -> Unit
                }
            }
        }
    }

}