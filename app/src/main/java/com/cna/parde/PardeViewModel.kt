package com.cna.parde

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cna.parde.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PardeViewModel(private val pardeRepository: PardeRepository): ViewModel() {

    init {
        fetchPopularMovies()
    }

    val popularMovies: LiveData<List<Movie>>
        get() = pardeRepository.movies

    fun getError(): LiveData<String> = pardeRepository.error

    private fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchMovies()
        }
    }
}