package com.lucas.marvelheroesapp

import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.data.models.Data
import com.lucas.marvelheroes.domain.MarvelComicsRepository
import com.lucas.marvelheroes.util.MarvelComicsResource
import javax.inject.Inject

class FakeMarvelComicsRepository @Inject constructor() : MarvelComicsRepository {

    override suspend fun getMarvelComics(): MarvelComicsResource<ComicResponse> {
        val fakeComic = Comic(
            1,
            "Spider-Man",
        )

        return MarvelComicsResource.Success(ComicResponse("", Data(10, listOf(fakeComic))))
    }
}