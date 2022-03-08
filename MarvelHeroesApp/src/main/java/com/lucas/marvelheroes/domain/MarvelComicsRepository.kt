package com.lucas.marvelheroes.domain

import com.lucas.marvelheroes.data.MarvelComicsServiceInstance
import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.util.ComicsResource
import java.lang.Exception
import javax.inject.Inject

/**
 * Module will provide MarvelHeroesServiceInstance instance for us since it is available at
 * application level for the whole app to use
 */

class MarvelComicsRepository @Inject constructor(
    private val apiInstance: MarvelComicsServiceInstance
) : ComicsRepository {

    override suspend fun getComics(): ComicsResource<ComicResponse> {
        return try {
            val response = apiInstance.getMarvelComics()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                ComicsResource.Success(result)
            } else {
                ComicsResource.Error(response.message())
            }

        } catch (e: Exception) {
            ComicsResource.Error(e.message ?: "An error occurred")
        }
    }
}