package com.lucas.marvelheroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.domain.MarvelHeroesRepository
import com.lucas.marvelheroes.util.MarvelHeroesDispatcherProvider
import com.lucas.marvelheroes.util.MarvelHeroesResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class MarvelHeroesListEvent {
    class Success(val comicsList: List<Comic>) : MarvelHeroesListEvent()
    class Failure(val message: String?) : MarvelHeroesListEvent()
    object Loading : MarvelHeroesListEvent()
    object Empty : MarvelHeroesListEvent()
}

@HiltViewModel
class MarvelHeroesListViewModel(
    private val repository: MarvelHeroesRepository,
    private val dispatchers: MarvelHeroesDispatcherProvider
) : ViewModel() {

    private val _conversion = MutableStateFlow<MarvelHeroesListEvent>(MarvelHeroesListEvent.Empty)
    val conversion: StateFlow<MarvelHeroesListEvent> = _conversion

    fun onViewCreated() = viewModelScope.launch(dispatchers.io) {
        _conversion.value = MarvelHeroesListEvent.Loading

        when (val marvelHeroesResponse = repository.getMarvelHeroesComics()) {
            is MarvelHeroesResource.Success -> {
                val comicsList = marvelHeroesResponse.resultData?.data?.results

                if (comicsList == null) {
                    _conversion.value =
                        MarvelHeroesListEvent.Failure("Unexpected error. The heroes are saving the world right now")
                } else {
                    _conversion.value = MarvelHeroesListEvent.Success(comicsList)
                }
            }
            is MarvelHeroesResource.Error -> {
                _conversion.value = MarvelHeroesListEvent.Failure(marvelHeroesResponse.message)
            }
        }
    }

}