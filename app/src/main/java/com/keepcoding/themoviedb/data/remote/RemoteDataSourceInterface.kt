package com.keepcoding.themoviedb.data.remote

import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.TvShowNetwork

interface RemoteDataSourceInterface {
    suspend fun getRemotePopularMovies(): List<MovieNetwork>
    suspend fun getRemotePopularTvShows(): List<TvShowNetwork>
}