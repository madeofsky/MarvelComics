package com.lucas.marvelheroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.domain.ComicsRepository
import com.lucas.marvelheroes.util.ComicsDispatcherProvider
import com.lucas.marvelheroes.util.ComicsListEvent
import com.lucas.marvelheroes.util.ComicsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsListViewModel @Inject constructor(
    private val repository: ComicsRepository,
    private val dispatchers: ComicsDispatcherProvider
) : ViewModel() {

    private val _comics = MutableStateFlow<ComicsListEvent>(ComicsListEvent.Empty)
    val comics: StateFlow<ComicsListEvent> = _comics

    fun onViewCreated() = viewModelScope.launch(dispatchers.io) {
        _comics.value = ComicsListEvent.Loading

        when (val comicsResponse = repository.getComics()) {
            is ComicsResource.Success -> {
                val comicsList = comicsResponse.resultData?.data?.results

                if (comicsList == null) {
                    _comics.value =
                        ComicsListEvent.Failure("Error. The heroes are saving the world right now.")
                } else {
                    _comics.value = ComicsListEvent.Success(comicsList)
                }
            }
            is ComicsResource.Error -> {
                _comics.value = ComicsListEvent.Failure(comicsResponse.message)
            }
        }
    }

    fun onMarvelHeroCardSelect(comic: Comic) {}

}