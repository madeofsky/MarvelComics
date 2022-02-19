package com.lucas.marvelheroes.api

import com.lucas.marvelheroes.utils.MarvelHeroesConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelHeroesAPIService {
    private val comicsAPIInstance: ComicsAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MarvelHeroesConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        comicsAPIInstance = retrofit.create(ComicsAPI::class.java)
    }

}