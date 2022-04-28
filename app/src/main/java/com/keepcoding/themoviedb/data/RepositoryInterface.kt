package com.keepcoding.themoviedb.data

import com.keepcoding.themoviedb.model.MoviePresentation
import com.keepcoding.themoviedb.model.TvShowPresentation

interface RepositoryInterface {
    suspend fun getRepositoryPopularMovies(): List<MoviePresentation>
    suspend fun getRepositoryPopularTvShows(): List<TvShowPresentation>
}