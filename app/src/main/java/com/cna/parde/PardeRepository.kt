package com.cna.parde

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cna.parde.api.MovieService
import com.cna.parde.model.PopularMovie
import com.cna.parde.model.UCMovie

class PardeRepository(private val movieService: MovieService) {

    private val apiKey = "721e4f18664869b21649e0be3e99ec59"

    private val popularMovieLiveData = MutableLiveData<List<PopularMovie>>()
    private val popularMovieErrorLiveData = MutableLiveData<String>()

    val popularMovies: LiveData<List<PopularMovie>>
        get() = popularMovieLiveData

    val popularMovieError: LiveData<String>
        get() = popularMovieErrorLiveData

    private val upcomingMovieLiveData = MutableLiveData<List<UCMovie>>()
    private val upcomingMovieErrorLiveData = MutableLiveData<String>()

    val upcomingMovies: LiveData<List<UCMovie>>
        get() = upcomingMovieLiveData
    val upcomingMovieError: LiveData<String>
        get() = upcomingMovieErrorLiveData

    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovie(apiKey)
            val upcomingMovies = movieService.getUpcomingMovie(apiKey)
            popularMovieLiveData.postValue(popularMovies.results)
            upcomingMovieLiveData.postValue(upcomingMovies.results)
        } catch (exception: Exception) {
            popularMovieErrorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}