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
import com.cna.parde.model.NPMovie
import com.cna.parde.model.POPMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie


class MovieFragment : Fragment() {

    private lateinit var recyclerViewTRMovie: RecyclerView

    private lateinit var recyclerViewNPMovie: RecyclerView

    private lateinit var recyclerViewPOPMovie: RecyclerView

    private lateinit var recyclerViewUCMovie: RecyclerView

    private lateinit var recyclerViewTMovie: RecyclerView

    private val npHomeAdapter = HomeAdapter(object :
        HomeAdapter.HomeClickListener<NPMovie> {
        override fun onClick(movie: NPMovie) {
            openNPMovieDetails(movie)
        }
    })

    private val ucHomeAdapter = HomeAdapter(object :
        HomeAdapter.HomeClickListener<UCMovie> {
        override fun onClick(movie: UCMovie) {
            openUCMovieDetails(movie)
        }
    })

    private val trHomeAdapter = HomeAdapter(object :
        HomeAdapter.HomeClickListener<TRMovie> {
        override fun onClick(movie: TRMovie) {
            openTRMovieDetails(movie)
        }
    })

    private val popHomeAdapter = HomeAdapter(object :
        HomeAdapter.HomeClickListener<POPMovie> {
        override fun onClick(movie: POPMovie) {
            openPOPMovieDetails(movie)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        })[PardeViewModel::class.java]

        recyclerViewNPMovie = view.findViewById(R.id.Recycler_movie_now_playing)
        recyclerViewNPMovie.adapter = npHomeAdapter
        pardeViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { nowPlayingMovie ->
            npHomeAdapter.addMovies(nowPlayingMovie)
        }
        pardeViewModel.getNowPlayingMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewUCMovie = view.findViewById(R.id.Recycler_movie_upComing)
        recyclerViewUCMovie.adapter = ucHomeAdapter
        pardeViewModel.upComingMovie.observe(viewLifecycleOwner) { upcomingMovie ->
            ucHomeAdapter.addMovies(upcomingMovie)
        }
        pardeViewModel.getUpComingMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewTRMovie = view.findViewById(R.id.Recycler_movie_top_rated)
        recyclerViewTRMovie.adapter = trHomeAdapter
        pardeViewModel.topRatedMovie.observe(viewLifecycleOwner) { topRatedMovie ->
            trHomeAdapter.addMovies(topRatedMovie)
        }
        pardeViewModel.getTopRatedMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewPOPMovie = view.findViewById(R.id.Recycler_movie_popular)
        recyclerViewPOPMovie.adapter = popHomeAdapter
        pardeViewModel.popularMovies.observe(viewLifecycleOwner) { popularMovie ->
            popHomeAdapter.addMovies(popularMovie)
        }
        pardeViewModel.getPopularMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun openNPMovieDetails(movie: NPMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.NPMovie, movie)
        }
        startActivity(intent)
    }

    private fun openUCMovieDetails(movie: UCMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.UCMovie, movie)
        }
        startActivity(intent)
    }

    private fun openTRMovieDetails(movie: TRMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.TRMovie, movie)
        }
        startActivity(intent)
    }

    private fun openPOPMovieDetails(movie: POPMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.POPMovie, movie)
        }
        startActivity(intent)
    }
}