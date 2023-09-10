package com.cna.parde.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.GMovieAdapter
import com.cna.parde.model.GMovie
import com.cna.parde.model.TMovie
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MovieGFragment : Fragment() {

    private lateinit var chipAction: Chip

    private lateinit var recyclerViewGMovie: RecyclerView

    private val gMovieAdapter by lazy {
        GMovieAdapter(object :GMovieAdapter.GMovieClickListener {
            override fun onGMovieClick(movie: GMovie) {
                openGMovieDetails(movie)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_g, container, false)
        chipAction = view.findViewById(R.id.actionChip)

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository)as T
            }
        }).get(PardeViewModel::class.java)

        recyclerViewGMovie = view.findViewById(R.id.Recycler_movie_genre)
        recyclerViewGMovie.adapter = gMovieAdapter

        chipAction.setOnClickListener {
            pardeViewModel.genreId = 28
            pardeViewModel.genreMovie.observe(viewLifecycleOwner) { genreMovie->
                gMovieAdapter.addMovies(genreMovie)
            }
        }

        return view
    }

    private fun openGMovieDetails(movie: GMovie) {
        Toast.makeText(requireContext(), movie.title, Toast.LENGTH_SHORT).show()
    }
}