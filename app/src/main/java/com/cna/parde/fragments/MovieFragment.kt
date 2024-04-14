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
import com.cna.parde.adapters.MovieAdapter
import com.cna.parde.constant.Constant
import com.cna.parde.detailActivity.DetailMovieActivity
import com.cna.parde.model.DisplayableItem
import com.cna.parde.model.NPMovie
import com.cna.parde.model.POPMovie
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie


class MovieFragment : Fragment() {

    private lateinit var recyclerViewTRMovie: RecyclerView

    private lateinit var recyclerViewNPMovie: RecyclerView

    private lateinit var recyclerViewPOPMovie: RecyclerView

    private lateinit var recyclerViewUCMovie: RecyclerView

    private lateinit var recyclerViewTMovie: RecyclerView

    private val npMovieAdapter = MovieAdapter(object :
        MovieAdapter.MovieClickListener<NPMovie> {
        override fun onMovieClick(movie: NPMovie) {
            openNPMovieDetails(movie)
        }
    })

    private val ucMovieAdapter = MovieAdapter(object :
        MovieAdapter.MovieClickListener<UCMovie> {
        override fun onMovieClick(movie: UCMovie) {
            openUCMovieDetails(movie)
        }
    })

    private val trMovieAdapter = MovieAdapter(object :
        MovieAdapter.MovieClickListener<TRMovie> {
        override fun onMovieClick(movie: TRMovie) {
            openTRMovieDetails(movie)
        }
    })

    private val popMovieAdapter = MovieAdapter(object :
        MovieAdapter.MovieClickListener<POPMovie> {
        override fun onMovieClick(movie: POPMovie) {
            openPOPMovieDetails(movie)
        }
    })

    private val tMovieAdapter = MovieAdapter(object :
        MovieAdapter.MovieClickListener<TMovie> {
        override fun onMovieClick(movie: TMovie) {
            openTMovieDetails(movie)
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
        recyclerViewNPMovie.adapter = npMovieAdapter
        pardeViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { nowPlayingMovie ->
            npMovieAdapter.addMovies(nowPlayingMovie)
        }
        pardeViewModel.getNowPlayingMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewUCMovie = view.findViewById(R.id.Recycler_movie_upComing)
        recyclerViewUCMovie.adapter = ucMovieAdapter
        pardeViewModel.upComingMovie.observe(viewLifecycleOwner) { upcomingMovie ->
            ucMovieAdapter.addMovies(upcomingMovie)
        }
        pardeViewModel.getUpComingMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewTRMovie = view.findViewById(R.id.Recycler_movie_top_rated)
        recyclerViewTRMovie.adapter = trMovieAdapter
        pardeViewModel.topRatedMovie.observe(viewLifecycleOwner) { topRatedMovie ->
            trMovieAdapter.addMovies(topRatedMovie)
        }
        pardeViewModel.getTopRatedMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewPOPMovie = view.findViewById(R.id.Recycler_movie_popular)
        recyclerViewPOPMovie.adapter = popMovieAdapter
        pardeViewModel.popularMovies.observe(viewLifecycleOwner) { popularMovie ->
            popMovieAdapter.addMovies(popularMovie)
        }
        pardeViewModel.getPopularMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewTMovie = view.findViewById(R.id.Recycler_movie_trending)
        recyclerViewTMovie.adapter = tMovieAdapter
        pardeViewModel.trendingMovie.observe(viewLifecycleOwner) { trendingMovie ->
            tMovieAdapter.addMovies(trendingMovie)
        }
        pardeViewModel.getTrendingMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun openNPMovieDetails(movie: NPMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.NPMovie, movie)
        }
        startActivity(
            intent, ActivityOptions
                .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle()
        )
    }

    private fun openUCMovieDetails(movie: UCMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.UCMovie, movie)
        }
        startActivity(
            intent, ActivityOptions
                .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle()
        )
    }

    private fun openTRMovieDetails(movie: TRMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.TRMovie, movie)
        }
        startActivity(
            intent, ActivityOptions
                .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle()
        )
    }

    private fun openPOPMovieDetails(movie: POPMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.POPMovie, movie)
        }
        startActivity(
            intent, ActivityOptions
                .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle()
        )
    }

    private fun openTMovieDetails(movie: TMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(Constant.TMovie, movie)
        }
        startActivity(
            intent, ActivityOptions
                .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle()
        )
    }
}