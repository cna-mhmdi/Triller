package com.cna.parde.fragments

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.HomeAdapter
import com.cna.parde.constant.Constant
import com.cna.parde.detailActivity.DetailMovieActivity
import com.cna.parde.model.DetailMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPTv
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv

class TvFragment : Fragment() {

    private lateinit var recyclerViewOntTv: RecyclerView
    private lateinit var recyclerViewPopTv: RecyclerView
    private lateinit var recyclerViewTrTv: RecyclerView
    private lateinit var recyclerViewTTv: RecyclerView

    private val otaTvAdapter by lazy {
        HomeAdapter(object : HomeAdapter.HomeClickListener<OTATv> {
            override fun onClick(movie: OTATv) {
                openOTATvDetails(movie)
            }
        })
    }

    private val tTvAdapter by lazy {
        HomeAdapter(object : HomeAdapter.HomeClickListener<TTv> {
            override fun onClick(movie: TTv) {
                openTTvDetails(movie)
            }
        })
    }

    private val popTvAdapter by lazy {
        HomeAdapter(object : HomeAdapter.HomeClickListener<POPTv> {
            override fun onClick(movie: POPTv) {
                openPOPTvDetails(movie)
            }
        })
    }

    private val trTvAdapter by lazy {
        HomeAdapter(object : HomeAdapter.HomeClickListener<TRTv> {
            override fun onClick(movie: TRTv) {
                openTRTvDetails(movie)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv, container, false)

        recyclerViewOntTv = view.findViewById(R.id.Recycler_tv_onTheAir)
        recyclerViewOntTv.adapter = otaTvAdapter

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        pardeViewModel.onTheAirTv.observe(viewLifecycleOwner) { onTheAirTv ->
            otaTvAdapter.addMovies(onTheAirTv)
        }

        pardeViewModel.getOnTheAirTvError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewPopTv = view.findViewById(R.id.Recycler_tv_popular)
        recyclerViewPopTv.adapter = popTvAdapter

        pardeViewModel.popularTv.observe(viewLifecycleOwner) { popularTv ->
            popTvAdapter.addMovies(popularTv)
        }

        pardeViewModel.getPopularTvError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewTrTv = view.findViewById(R.id.Recycler_tv_toprated)
        recyclerViewTrTv.adapter = trTvAdapter

        pardeViewModel.topRatedTv.observe(viewLifecycleOwner) { topRatedTv ->
            trTvAdapter.addMovies(topRatedTv)
        }
        pardeViewModel.getTopRatedTvError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewTTv = view.findViewById(R.id.Recycler_tv_trending)
        recyclerViewTTv.adapter = tTvAdapter

        pardeViewModel.trendingTv.observe(viewLifecycleOwner) { trendingTv ->
            tTvAdapter.addMovies(trendingTv)
        }

        pardeViewModel.getTrendingTvError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun openOTATvDetails(tv: OTATv) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.OTATv, tv)
        }
        startActivity(intent)
    }

    private fun openPOPTvDetails(tv: POPTv) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.POPTv, tv)
        }
        startActivity(intent)
    }

    private fun openTRTvDetails(tv: TRTv) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.TRTv, tv)
        }
        startActivity(intent)
    }

    private fun openTTvDetails(tv: TTv) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.TTv, tv)
        }
        startActivity(intent)
    }
}