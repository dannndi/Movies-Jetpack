package com.example.movies.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.FragmentMovieFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoriteFragment : Fragment() {

    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviePagedListAdapter: MoviePagedListAdapter
    private val viewModel: MovieFavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviePagedListAdapter = MoviePagedListAdapter(requireActivity())

        viewModel.getFavoriteMovies().observe(viewLifecycleOwner) { movieListPaged ->
            if (movieListPaged != null) {
                moviePagedListAdapter.submitList(movieListPaged)
            }
        }

        binding.rvMovie.apply {
            adapter = moviePagedListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }


    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}