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
import com.cna.parde.adapters.NPMovieAdapter
import com.cna.parde.adapters.POPMovieAdapter
import com.cna.parde.adapters.TMovieAdapter
import com.cna.parde.adapters.TRMovieAdapter
import com.cna.parde.adapters.UCMovieAdapter
import com.cna.parde.detailActivity.DetailMovieActivity
import com.cna.parde.model.NPMovie
import com.cna.parde.model.POPMovie
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie

class MovieFragment : Fragment() {

    private lateinit var recyclerViewOTAMovie: RecyclerView

    private lateinit var recyclerViewFYMovie: RecyclerView

    private lateinit var recyclerViewUCMovie: RecyclerView

    private lateinit var recyclerViewPOPMovie: RecyclerView

    private lateinit var recyclerViewTMovie: RecyclerView

    private val npMovieAdapter by lazy {
        NPMovieAdapter(object : NPMovieAdapter.NPMovieClickListener {
            override fun onNPMovieClick(movie: NPMovie) {
                openNPMovieDetails(movie)
            }
        })
    }

    private val ucMovieAdapter by lazy {
        UCMovieAdapter(object : UCMovieAdapter.UCMovieClickListener {
            override fun onUCMovieClick(movie: UCMovie) {
                openUCMovieDetails(movie)
            }
        })
    }

    private val trMovieAdapter by lazy {
        TRMovieAdapter(object : TRMovieAdapter.TRMovieClickListener {
            override fun onTRMovieClick(movie: TRMovie) {
                openTRMovieDetails(movie)
            }
        })
    }

    private val popMovieAdapter by lazy {
        POPMovieAdapter(object : POPMovieAdapter.POPMovieClickListener {
            override fun onPOPMovieClick(movie: POPMovie) {
                openPOPMovieDetails(movie)
            }
        })
    }

    private val tMovieAdapter by lazy {
        TMovieAdapter(object : TMovieAdapter.TMovieClickListener {
            override fun onTMovieClick(movie: TMovie) {
                openTMovieDetails(movie)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        recyclerViewOTAMovie = view.findViewById(R.id.Recycler_OnTheAir)
        recyclerViewOTAMovie.adapter = npMovieAdapter

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        pardeViewModel.nowPlayingMovies.observe(viewLifecycleOwner) { nowPlayingMovie ->
            npMovieAdapter.addMovies(nowPlayingMovie)
        }
        pardeViewModel.getNowPlayingMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewUCMovie = view.findViewById(R.id.Recycler_UpComing)
        recyclerViewUCMovie.adapter = ucMovieAdapter

        pardeViewModel.upComingMovie.observe(viewLifecycleOwner) { upcomingMovie ->
            ucMovieAdapter.addMovies(upcomingMovie)
        }
        pardeViewModel.getUpComingMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewFYMovie = view.findViewById(R.id.Recycler_ForYou)
        recyclerViewFYMovie.adapter = trMovieAdapter

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
            putExtra(DetailMovieActivity.NPMovie, movie)
        }
        startActivity(intent, ActivityOptions
            .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle())
    }

    private fun openUCMovieDetails(movie: UCMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.UCMovie, movie)
        }
        startActivity(intent, ActivityOptions
            .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle())
    }

    private fun openTRMovieDetails(movie: TRMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.TRMovie, movie)
        }
        startActivity(intent, ActivityOptions
            .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle())
    }

    private fun openPOPMovieDetails(movie: POPMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.POPMovie, movie)
        }
        startActivity(intent, ActivityOptions
            .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle())

    }

    private fun openTMovieDetails(movie: TMovie) {
        val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.TMovie, movie)
        }
        startActivity(intent, ActivityOptions
            .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle())
    }

//    private fun openMovieDetails(movieType: String, movieId: ) {
//        val intent = Intent(requireContext(),DetailMovieActivity::class.java).apply {
//            putExtra(DetailMovieActivity.NPMovie,movie)
//        }
//        startActivity(intent)
//    }

}