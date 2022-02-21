package com.lucas.marvelheroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.domain.MarvelComicsRepository
import com.lucas.marvelheroes.util.MarvelComicsDispatcherProvider
import com.lucas.marvelheroes.util.MarvelComicsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MarvelComicsListEvent {
    class Success(val comicsList: List<Comic>) : MarvelComicsListEvent()
    class Failure(val message: String?) : MarvelComicsListEvent()
    object Loading : MarvelComicsListEvent()
    object Empty : MarvelComicsListEvent()
}

@HiltViewModel
class MarvelComicsListViewModel @Inject constructor(
    private val repository: MarvelComicsRepository,
    private val dispatchers: MarvelComicsDispatcherProvider
) : ViewModel() {

    private val _comics = MutableStateFlow<MarvelComicsListEvent>(MarvelComicsListEvent.Empty)
    val comics: StateFlow<MarvelComicsListEvent> = _comics

    fun onViewCreated() = viewModelScope.launch(dispatchers.io) {
        _comics.value = MarvelComicsListEvent.Loading

        when (val marvelComicsResponse = repository.getMarvelComics()) {
            is MarvelComicsResource.Success -> {
                val comicsList = marvelComicsResponse.resultData?.data?.results

                if (comicsList == null) {
                    _comics.value =
                        MarvelComicsListEvent.Failure("Unexpected error. The heroes are saving the world right now")
                } else {
                    _comics.value = MarvelComicsListEvent.Success(comicsList)
                }
            }
            is MarvelComicsResource.Error -> {
                _comics.value = MarvelComicsListEvent.Failure(marvelComicsResponse.message)
            }
        }
    }

    fun onMarvelHeroCardSelect(comic: Comic) {}

}