package com.lucas.marvelheroes.data

import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.util.MarvelHeroesConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelHeroesServiceInstance {

    @GET("comics")
    suspend fun getMarvelHeroesComics(
        @Query("ts") ts: String = MarvelHeroesConstants.TS,
        @Query("apikey") apiKey: String = MarvelHeroesConstants.API_KEY,
        @Query("hase") hash: String = MarvelHeroesConstants.hash(),
        @Query("limit") limit: String = MarvelHeroesConstants.LIMIT
    ): Response<ComicResponse>
}