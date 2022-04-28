package com.keepcoding.themoviedb.data

import com.keepcoding.themoviedb.model.MovieLocal
import com.keepcoding.themoviedb.model.MovieNetwork
import javax.inject.Inject

class NetworkToLocalMapper @Inject constructor() {

    fun mapToLocal(movieNetwork: MovieNetwork): MovieLocal {
        return MovieLocal(
            uid = null,
            title = movieNetwork.title,
            popularity = movieNetwork.popularity,
            posterPath = movieNetwork.posterPath,
            voteCount = movieNetwork.voteCount
        )
    }
}