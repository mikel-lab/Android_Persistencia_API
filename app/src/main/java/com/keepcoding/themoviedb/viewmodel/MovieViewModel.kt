package com.keepcoding.themoviedb.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.MoviePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data1: MutableLiveData<List<MovieNetwork>> = MutableLiveData()
    val data: MutableLiveData<List<MoviePresentation>> = MutableLiveData()

    val numberOfAppOpened: MutableLiveData<Int> = MutableLiveData()

    fun getViewModelPopularMovies() {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getRepositoryPopularMovies()
            }

            data.postValue(movies)
        }
    }


}

