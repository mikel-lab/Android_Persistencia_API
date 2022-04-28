package com.keepcoding.themoviedb.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.themoviedb.R
import com.keepcoding.themoviedb.databinding.MoviesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var binding: MoviesFragmentBinding

    private val viewModel: MoviesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.movies_fragment, container, false)
        binding = MoviesFragmentBinding.bind(view)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterChachi = MovieAdapter(ArrayList(), { movie ->
            // Lanzar pantalla de detalle
            Log.d("MOVIE_CKICK", movie.title)
        })

        binding.myRecycler.adapter = adapterChachi
        binding.myRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.getViewModelPopularMovies()
        viewModel.data.observe(viewLifecycleOwner) {
            adapterChachi.updateMovies(it)
        }
    }
}