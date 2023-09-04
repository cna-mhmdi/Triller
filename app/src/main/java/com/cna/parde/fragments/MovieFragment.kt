package com.cna.parde.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.MainActivity
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.FYMovieAdapter
import com.cna.parde.adapters.OTAMovieAdapter
import com.cna.parde.adapters.UCMovieAdapter
import com.cna.parde.model.PopularMovie
import com.cna.parde.model.UCMovie

class MovieFragment : Fragment() {

    private lateinit var recyclerViewOTAMovie : RecyclerView

    private lateinit var recyclerViewFYMovie: RecyclerView
    private lateinit var adapterFYMovie: FYMovieAdapter

    private lateinit var recyclerViewUCMovie: RecyclerView

    private val otaMovieAdapter by lazy {
        OTAMovieAdapter(object : OTAMovieAdapter.OTAMovieClickListener {
            override fun onOTAMovieClick(movie: PopularMovie) {
                openOTAMovieDetails(movie)
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        recyclerViewOTAMovie = view.findViewById(R.id.Recycler_OnTheAir)
        recyclerViewOTAMovie.adapter = otaMovieAdapter

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        //attachment for PopularMovies
        pardeViewModel.popularMovies.observe(requireActivity()) { popularMovie ->
            otaMovieAdapter.addMovies(popularMovie)
        }
        pardeViewModel.getPopularMovieError().observe(requireActivity()) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show()
        }

        recyclerViewUCMovie = view.findViewById(R.id.Recycler_UpComing)
        recyclerViewUCMovie.adapter = ucMovieAdapter

        //attachment for PopularMovies
        pardeViewModel.upComingMovie.observe(requireActivity()) { upcomingMovie ->
            ucMovieAdapter.addMovies(upcomingMovie)
        }
        pardeViewModel.getUpComingMovieError().observe(requireActivity()) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG).show()
        }

        recyclerViewFYMovie = view.findViewById(R.id.Recycler_ForYou)
        adapterFYMovie = FYMovieAdapter(MainActivity.nameFY, MainActivity.ratesFY)
        val layoutManagerFY = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL,false)
        recyclerViewFYMovie.layoutManager = layoutManagerFY
        recyclerViewFYMovie.adapter = adapterFYMovie


        return view
    }

    private fun openOTAMovieDetails(movie: PopularMovie) {
        Toast.makeText(requireActivity(),movie.title, Toast.LENGTH_LONG).show()
    }

    private fun openUCMovieDetails(movie: UCMovie) {
        Toast.makeText(requireActivity(),movie.title, Toast.LENGTH_LONG).show()
    }

}