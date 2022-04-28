package com.keepcoding.themoviedb.data

import com.keepcoding.themoviedb.model.MovieLocal
import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.MoviePresentation
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {

    fun mapToPresentation(movieLocal: MovieLocal): MoviePresentation {
        return MoviePresentation(
            title = movieLocal.title,
            posterPath = movieLocal.posterPath,
            voteCount = movieLocal.voteCount
        )
    }
}