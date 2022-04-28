package com.keepcoding.themoviedb.model

import com.squareup.moshi.Json

data class PopularMoviesResponse(
    @Json(name = "total_result") val totalResult: Int?,
    @Json(name = "results") val results: List<MovieNetwork>
)
