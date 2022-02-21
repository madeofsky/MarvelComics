package com.lucas.marvelheroes.util

import com.lucas.marvelheroes.data.models.Comic

sealed class MarvelComicsResource<T>(val resultData: T?, val message: String?) {
    class Success<T>(resultData: T) : MarvelComicsResource<T>(resultData, null)
    class Error<T>(message: String) : MarvelComicsResource<T>(null, message)
}

sealed class MarvelComicsListEvent {
    class Success(val comicsList: List<Comic>) : MarvelComicsListEvent()
    class Failure(val message: String?) : MarvelComicsListEvent()
    object Loading : MarvelComicsListEvent()
    object Empty : MarvelComicsListEvent()
}