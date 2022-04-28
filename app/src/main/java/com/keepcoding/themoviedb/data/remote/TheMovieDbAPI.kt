package com.keepcoding.themoviedb.data.remote

import com.keepcoding.themoviedb.model.PopularMoviesResponse
import com.keepcoding.themoviedb.model.PopularTvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbAPI {
    @GET("/3/movie/popular")
    suspend fun getTheMovieDBPopularMovies(): PopularMoviesResponse

    @GET("/3/tv/popular")
    suspend fun getTheMovieDBPopularTvShows(): PopularTvShowsResponse
}