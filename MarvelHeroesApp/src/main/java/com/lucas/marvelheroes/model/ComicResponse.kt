package com.lucas.marvelheroes.model

import com.google.gson.annotations.SerializedName

data class ComicResponse(
    val copyright: String,
    @SerializedName("data")
    val data: Data? = null
)