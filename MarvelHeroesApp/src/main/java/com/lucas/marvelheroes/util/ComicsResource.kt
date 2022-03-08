package com.lucas.marvelheroes.util

import com.lucas.marvelheroes.data.models.Comic

sealed class ComicsResource<T>(val resultData: T?, val message: String?) {
    class Success<T>(resultData: T) : ComicsResource<T>(resultData, null)
    class Error<T>(message: String) : ComicsResource<T>(null, message)
}

sealed class ComicsListEvent {
    class Success(val comicsList: List<Comic>) : ComicsListEvent()
    class Failure(val message: String?) : ComicsListEvent()
    object Loading : ComicsListEvent()
    object Empty : ComicsListEvent()
}