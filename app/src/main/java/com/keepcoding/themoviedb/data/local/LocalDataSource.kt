package com.keepcoding.themoviedb.data.local

import com.keepcoding.themoviedb.model.MovieLocal
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: MoviesDao,
    private val preferencesManager: PreferencesManager
) {

    fun getAllMovies(): List<MovieLocal> {
        return dao.getAll()
    }

    fun insertMovie(movieLocal: MovieLocal) {
        dao.insert(movieLocal)
    }

    fun getNumberOfAppOpened(): Int {
        return preferencesManager.getNumberOfAppOpened()
    }

    fun increaseNumberOfAppOpened() {
        val oldValue = preferencesManager.getNumberOfAppOpened()
        preferencesManager.setNumberOfAppOpened(oldValue + 1)
    }

    fun deleteMovie(movieLocal: MovieLocal){
        dao.delete(movieLocal)
    }
}
