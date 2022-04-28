package com.keepcoding.themoviedb.model

import com.squareup.moshi.Json

data class TvShowNetwork(
    @Json(name = "name") val name: String = "",
    @Json(name = "popularity") val popularity: Double = 0.0,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "vote_count") val voteCount: Int = 0,
)


// Funcion de extension para implementar un mapper
fun TvShowNetwork.toPresentation(): TvShowPresentation {
    return TvShowPresentation(name, posterPath, voteCount)
}
