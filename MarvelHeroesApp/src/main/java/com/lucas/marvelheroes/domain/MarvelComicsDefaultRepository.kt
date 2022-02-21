package com.lucas.marvelheroes.domain

import com.lucas.marvelheroes.data.MarvelComicsServiceInstance
import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.util.MarvelComicsResource
import java.lang.Exception
import javax.inject.Inject

/**
 * Module will provide MarvelHeroesServiceInstance instance for us since it is available at
 * application level for the whole app to use
 */

class MarvelComicsDefaultRepository @Inject constructor(
    private val apiInstance: MarvelComicsServiceInstance
) : MarvelComicsRepository {

    override suspend fun getMarvelComics(): MarvelComicsResource<ComicResponse> {
        return try {
            val response = apiInstance.getMarvelHeroesComics()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                MarvelComicsResource.Success(result)
            } else {
                MarvelComicsResource.Error(response.message())
            }

        } catch (e: Exception) {
            MarvelComicsResource.Error(e.message ?: "An error occurred")
        }
    }
}