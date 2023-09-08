package com.cna.parde

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cna.parde.api.PardeService
import com.cna.parde.model.NPMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie

class PardeRepository(private val pardeService: PardeService) {

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

    private val onTheAirTvLiveData = MutableLiveData<List<OTATv>>()
    private val onTheAirTvErrorLiveData = MutableLiveData<String>()

    val onTheAirTv: LiveData<List<OTATv>>
        get() = onTheAirTvLiveData
    val onTheAirTvError: LiveData<String>
        get() = onTheAirTvErrorLiveData


    suspend fun fetchMovies() {
        try {
            val nowPlayingMovies = pardeService.getNowPlayingMovie(apiKey)
            val upcomingMovies = pardeService.getUpcomingMovie(apiKey)
            val topRatedMovies = pardeService.getTopRatedMovie(apiKey)
            topRatedMovieLiveData.postValue(topRatedMovies.results)
            nowPlayingMovieLiveData.postValue(nowPlayingMovies.results)
            upcomingMovieLiveData.postValue(upcomingMovies.results)

            val onTheAirTv = pardeService.getOnTheAirTv(apiKey)
            onTheAirTvLiveData.postValue(onTheAirTv.results)


        } catch (exception: Exception) {
            TODO("remember to <list is up to date> ")
            nowPlayingMovieErrorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}