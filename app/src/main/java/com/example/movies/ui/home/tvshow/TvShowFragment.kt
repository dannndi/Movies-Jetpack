package com.example.movies.ui.home.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.data.model.TvShow
import com.example.movies.databinding.FragmentTvShowBinding
import com.example.movies.ui.detailtvshow.DetailTvShowActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var tvShowRvAdapter: TvShowRvAdapter

    private lateinit var tvShow: List<TvShow>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // start getting movie
        viewModel.getTvShowList()
        showLoading(true)

        tvShowRvAdapter = TvShowRvAdapter()

        viewModel.tvShowList.observe(viewLifecycleOwner) { tvList ->
            tvShow = tvList
            tvShowRvAdapter.setList(ArrayList(tvShow))
            showLoading(false)
        }

        binding.rvTvShow.apply {
            adapter = tvShowRvAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        tvShowRvAdapter.setOnClickListener { position ->
            Intent(activity, DetailTvShowActivity::class.java).also {
                it.putExtra("ID", tvShow[position].id)
                it.putExtra("TVSHOW", tvShow[position])
                startActivity(it)
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
            binding.rvTvShow.visibility = View.GONE
        } else {
            binding.rvTvShow.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
        }
    }
}