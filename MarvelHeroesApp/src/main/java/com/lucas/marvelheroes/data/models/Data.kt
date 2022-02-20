package com.lucas.marvelheroes.data.models

import com.google.gson.annotations.SerializedName

data class Data(
    val limit: Int,
    @SerializedName("results")
    val results: List<Comic>? = null
)