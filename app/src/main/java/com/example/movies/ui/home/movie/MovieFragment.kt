package com.example.movies.ui.home.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.data.model.Movie
import com.example.movies.databinding.FragmentMovieBinding
import com.example.movies.ui.detailmovie.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieTvRvAdapter: MovieRvAdapter

    private lateinit var movies: List<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // start getting movie
        viewModel.getMovies()
        showLoading(true)

        movieTvRvAdapter = MovieRvAdapter()

        viewModel.movieList.observe(viewLifecycleOwner) { mList ->
            if (mList.isNotEmpty()) {
                movies = mList
                movieTvRvAdapter.setList(ArrayList(mList))
                showLoading(false)
            }
        }

        binding.rvMovie.apply {
            adapter = movieTvRvAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        movieTvRvAdapter.setOnClickListener { position ->
            Intent(activity, DetailMovieActivity::class.java).apply {
                putExtra("ID", movies[position].id)
                putExtra("MOVIE", movies[position])
                startActivity(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun showLoading(boolean: Boolean) {
        if (boolean) {
            binding.loading.visibility = View.VISIBLE
            binding.rvMovie.visibility = View.GONE
        } else {
            binding.rvMovie.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
        }
    }
}