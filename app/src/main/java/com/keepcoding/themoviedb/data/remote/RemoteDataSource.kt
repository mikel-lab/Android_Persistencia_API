package com.keepcoding.themoviedb.data.remote

import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.TvShowNetwork
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val api: TheMovieDbAPI
): RemoteDataSourceInterface {

    override suspend fun getRemotePopularMovies(): List<MovieNetwork> {
        val response = api.getTheMovieDBPopularMovies()
        return response.results
    }

    override suspend fun getRemotePopularTvShows(): List<TvShowNetwork> {
        val response = api.getTheMovieDBPopularTvShows()
        return response.results
    }
}
