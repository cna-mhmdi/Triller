package com.cna.parde

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cna.parde.model.PopularMovie
import com.cna.parde.model.UCMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PardeViewModel(private val pardeRepository: PardeRepository): ViewModel() {

    init {
        fetchMovies()
    }

    val popularMovies: LiveData<List<PopularMovie>>
        get() = pardeRepository.popularMovies

    fun getPopularMovieError(): LiveData<String> = pardeRepository.popularMovieError

    val upComingMovie: LiveData<List<UCMovie>> get() = pardeRepository.upcomingMovies
    fun getUpComingMovieError(): LiveData<String> = pardeRepository.upcomingMovieError

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchMovies()
        }
    }
}