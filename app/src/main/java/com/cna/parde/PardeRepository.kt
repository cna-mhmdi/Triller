package com.cna.parde

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cna.parde.api.PopularMovieService
import com.cna.parde.model.Movie

class PardeRepository(private val popularMovieService: PopularMovieService) {

    private val apiKey = "721e4f18664869b21649e0be3e99ec59"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    private val errorLiveData = MutableLiveData<String>()

    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    val error: LiveData<String>
        get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            val popularMovies = popularMovieService.getPopularMovie(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
            errorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}