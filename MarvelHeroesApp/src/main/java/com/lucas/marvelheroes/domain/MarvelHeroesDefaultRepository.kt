package com.lucas.marvelheroes.domain

import com.lucas.marvelheroes.data.MarvelHeroesServiceInstance
import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.util.MarvelHeroesResource
import java.lang.Exception
import javax.inject.Inject

/**
 * Module will provide MarvelHeroesServiceInstance instance for us since it is available at
 * application level for the whole app to use
 */

class MarvelHeroesDefaultRepository @Inject constructor(
    private val apiInstance: MarvelHeroesServiceInstance
) : MarvelHeroesRepository {

    override suspend fun getMarvelHeroesComics(): MarvelHeroesResource<ComicResponse> {
        return try {
            val response = apiInstance.getMarvelHeroesComics()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                MarvelHeroesResource.Success(result)
            } else {
                MarvelHeroesResource.Error(response.message())
            }

        } catch (e: Exception) {
            MarvelHeroesResource.Error(e.message ?: "An error occurred")
        }
    }
}