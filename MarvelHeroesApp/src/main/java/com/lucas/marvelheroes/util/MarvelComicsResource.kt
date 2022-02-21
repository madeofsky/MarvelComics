package com.lucas.marvelheroes.util

sealed class MarvelComicsResource<T>(val resultData: T?, val message: String?) {
    class Success<T>(resultData: T) : MarvelComicsResource<T>(resultData, null)
    class Error<T>(message: String) : MarvelComicsResource<T>(null, message)
}