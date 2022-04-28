package com.keepcoding.themoviedb.view.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.MoviePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data: MutableLiveData<List<MoviePresentation>> = MutableLiveData()

    fun getViewModelPopularMovies() {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getRepositoryPopularMovies()
            }

            data.postValue(movies)
        }
    }

}
