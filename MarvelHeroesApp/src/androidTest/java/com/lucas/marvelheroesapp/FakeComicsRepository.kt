package com.lucas.marvelheroesapp

import com.lucas.marvelheroes.data.models.Comic
import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.data.models.Data
import com.lucas.marvelheroes.domain.ComicsRepository
import com.lucas.marvelheroes.util.ComicsResource
import javax.inject.Inject

class FakeComicsRepository @Inject constructor() : ComicsRepository {

    override suspend fun getComics(): ComicsResource<ComicResponse> {
        val fakeComic = Comic(
            1,
            "Spider-Man",
        )

        return ComicsResource.Success(ComicResponse("", Data(10, listOf(fakeComic))))
    }
}