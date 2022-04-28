package com.keepcoding.themoviedb.view.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.themoviedb.R
import com.keepcoding.themoviedb.databinding.TvShowsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowsFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowsFragment()
    }

    private lateinit var binding: TvShowsFragmentBinding

    private val viewModel: TvShowViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.tv_shows_fragment, container, false)

        binding = TvShowsFragmentBinding.bind(view)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterChachi = TvShowsAdapter(ArrayList())

        binding.tvShowList.adapter = adapterChachi
        binding.tvShowList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.getViewModelPopularTvShows()
        viewModel.data.observe(viewLifecycleOwner) {
            adapterChachi.updateMovies(it)
        }
    }
}
