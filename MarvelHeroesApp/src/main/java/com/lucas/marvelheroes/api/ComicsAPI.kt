package com.lucas.marvelheroes.api

import com.lucas.marvelheroes.utils.MarvelHeroesConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsAPI {
    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String = MarvelHeroesConstants.TS,
        @Query("apikey") apiKey: String = MarvelHeroesConstants.API_KEY,
        @Query("hase") hash: String = MarvelHeroesConstants.hash(),
        @Query("limit") limit: String = MarvelHeroesConstants.LIMIT
    )
}