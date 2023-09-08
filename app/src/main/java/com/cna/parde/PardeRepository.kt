package com.cna.parde

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cna.parde.api.MovieService
import com.cna.parde.model.NPMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie

class PardeRepository(private val movieService: MovieService) {

    private val apiKey = "721e4f18664869b21649e0be3e99ec59"

    private val nowPlayingMovieLiveData = MutableLiveData<List<NPMovie>>()
    private val nowPlayingMovieErrorLiveData = MutableLiveData<String>()

    val nowPlayingMovies: LiveData<List<NPMovie>>
        get() = nowPlayingMovieLiveData

    val nowPlayingMovieError: LiveData<String>
        get() = nowPlayingMovieErrorLiveData

    private val upcomingMovieLiveData = MutableLiveData<List<UCMovie>>()
    private val upcomingMovieErrorLiveData = MutableLiveData<String>()

    val upcomingMovies: LiveData<List<UCMovie>>
        get() = upcomingMovieLiveData
    val upcomingMovieError: LiveData<String>
        get() = upcomingMovieErrorLiveData

    private val topRatedMovieLiveData = MutableLiveData<List<TRMovie>>()
    private val topRatedMovieErrorLiveData = MutableLiveData<String>()

    val topRatedMovies: LiveData<List<TRMovie>>
        get() = topRatedMovieLiveData
    val topRatedMovieError: LiveData<String>
        get() = topRatedMovieErrorLiveData

    suspend fun fetchMovies() {
        try {
            val nowPlayingMovies = movieService.getNowPlayingMovie(apiKey)
            val upcomingMovies = movieService.getUpcomingMovie(apiKey)
            val topRatedMovies = movieService.getTopRatedMovie(apiKey)
            topRatedMovieLiveData.postValue(topRatedMovies.results)
            nowPlayingMovieLiveData.postValue(nowPlayingMovies.results)
            upcomingMovieLiveData.postValue(upcomingMovies.results)
        } catch (exception: Exception) {
            TODO("remember to <list is up to date> ")
            nowPlayingMovieErrorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}