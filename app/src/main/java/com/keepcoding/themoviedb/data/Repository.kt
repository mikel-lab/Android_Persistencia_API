package com.keepcoding.themoviedb.data

import android.util.Log
import com.keepcoding.themoviedb.data.local.LocalDataSource
import com.keepcoding.themoviedb.data.remote.RemoteDataSource
import com.keepcoding.themoviedb.model.MoviePresentation
import com.keepcoding.themoviedb.model.TvShowPresentation
import com.keepcoding.themoviedb.model.toPresentation
import javax.inject.Inject

// ViewModel -> Repository -> Sources
class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val networkToLocalMapper: NetworkToLocalMapper,
    private val localToPresentationMapper: LocalToPresentationMapper,
) : RepositoryInterface {

    override suspend fun getRepositoryPopularMovies(): List<MoviePresentation> {
        val localMovies = local.getAllMovies()

        Log.d("REPOSITORY:", "Tamaño inicial: ${localMovies.size}")

        if (localMovies.isEmpty()) {
            Log.d("REPOSITORY:", "Local vacío, pido a remote")
            val remoteMovies = remote.getRemotePopularMovies()

            Log.d("REPOSITORY:", "Tamaño de remote: ${remoteMovies.size}")

            remoteMovies.forEach {
                Log.d("REPOSITORY:", "Insertando en local $it")
                local.insertMovie(networkToLocalMapper.mapToLocal(it))
            }
        }
        val newLocalMovies = local.getAllMovies()
        Log.d("REPOSITORY:", "Tamaño final en local: ${newLocalMovies.size}")

        return newLocalMovies.map { localToPresentationMapper.mapToPresentation(it) }
    }

    override suspend fun getRepositoryPopularTvShows(): List<TvShowPresentation> {
        val remoteTvShows = remote.getRemotePopularTvShows()
        return remoteTvShows.map { it.toPresentation() }
    }

    // Pruebas que hemos hecho con room y el botón de la activity pero que no las vamos a usar en el
    // resto de la app. Por eso no están metidas dentro de la abstracción
    // ------------------------------------------------------------------------------------------ //
    fun getNumberOfAppOpened(): Int {
        return local.getNumberOfAppOpened()
    }

    fun increaseNumberOfAppOpened(){
        local.increaseNumberOfAppOpened()
    }

    fun deleteMovies() {
        local.getAllMovies().forEach {
            local.deleteMovie(it)
        }
    }

    fun deleteMovie(moviePresentation: MoviePresentation?) {
        val localMovies = local.getAllMovies()
        val localMovie = localMovies.first { moviePresentation?.title == it.title }

        local.deleteMovie(localMovie)
    }


    /* fun getMoviesWithFavorites(): List<Movie>{
         val remoteMovies = remote.getPopularMovies()
         val localMovies = local.getMovies()

         return ArrayList(remoteMovies.intersect(localMovies))
     }*/
    // ------------------------------------------------------------------------------------------ //

}