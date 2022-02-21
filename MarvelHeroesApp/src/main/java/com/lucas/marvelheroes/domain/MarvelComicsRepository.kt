package com.lucas.marvelheroes.domain

import com.lucas.marvelheroes.data.models.ComicResponse
import com.lucas.marvelheroes.util.MarvelComicsResource

/**
 * The reason why I've made it an interface is that, if I want to test it, I could provide a fake
 * implementation. Testing should be easier and quicker this way, avoiding doing actual requests
 */
interface MarvelComicsRepository {

    suspend fun getMarvelComics(): MarvelComicsResource<ComicResponse>
}