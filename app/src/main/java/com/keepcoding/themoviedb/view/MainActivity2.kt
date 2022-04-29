package com.keepcoding.themoviedb.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.keepcoding.themoviedb.R
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.viewmodel.MovieViewModel
import com.keepcoding.themoviedb.databinding.ActivityMain2Binding
import com.keepcoding.themoviedb.view.movies.MoviesFragment
import com.keepcoding.themoviedb.view.tvshows.TvShowsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, MoviesFragment.newInstance())
            .commit()

        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    // Lanzar fragment Peliculas
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, MoviesFragment.newInstance())
                        .commit()
                    true
                }
                R.id.page_2 -> {
                    // Lanzar fragment Series
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, TvShowsFragment.newInstance())
                        .commit()

                    true
                }
                else -> {

                    true
                }
            }

        }








        // MVVM - Model View ViewModel
    }

}