package com.lucas.marvelheroes.util

sealed class MarvelHeroesResource<T>(val resultData: T?, val message: String?) {
    class Success<T>(resultData: T) : MarvelHeroesResource<T>(resultData, null)
    class Error<T>(message: String) : MarvelHeroesResource<T>(null, message)
}