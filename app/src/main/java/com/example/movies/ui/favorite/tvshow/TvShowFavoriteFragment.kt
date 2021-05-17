package com.example.movies.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.FragmentTvShowFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFavoriteFragment : Fragment() {

    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var tvShowPagedListAdapter: TvShowPagedListAdapter
    private val viewModel: TvShowFavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowPagedListAdapter = TvShowPagedListAdapter(requireActivity())

        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { tvShowListPaged ->
            if (tvShowListPaged != null) {
                tvShowPagedListAdapter.submitList(tvShowListPaged)
            }
        }

        binding.rvTvShow.apply {
            adapter = tvShowPagedListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}