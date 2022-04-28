package com.keepcoding.themoviedb.view.tvshows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.TvShowPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data: MutableLiveData<List<TvShowPresentation>> = MutableLiveData()

    fun getViewModelPopularTvShows() {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getRepositoryPopularTvShows()
            }
            data.postValue(movies)
        }
    }
}
