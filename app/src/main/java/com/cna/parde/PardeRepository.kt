package com.cna.parde

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cna.parde.api.PardeService
import com.cna.parde.model.NPMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPTv
import com.cna.parde.model.TRMovie
import com.cna.parde.model.TRTv
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

    private val popularTvLiveData = MutableLiveData<List<POPTv>>()
    private val popularTvErrorLiveData = MutableLiveData<String>()

    val popularTv: LiveData<List<POPTv>>
        get() = popularTvLiveData
    val popularTvError: LiveData<String>
        get() = popularTvErrorLiveData

    private val topRatedTvLiveData = MutableLiveData<List<TRTv>>()
    private val topRatedTvErrorLiveData = MutableLiveData<String>()

    val topRatedTv: LiveData<List<TRTv>>
        get() = topRatedTvLiveData
    val topRatedTvError: LiveData<String>
        get() = topRatedTvErrorLiveData


    suspend fun fetchMovies() {
        try {
            val nowPlayingMovies = pardeService.getNowPlayingMovie(apiKey)
            val upcomingMovies = pardeService.getUpcomingMovie(apiKey)
            val topRatedMovies = pardeService.getTopRatedMovie(apiKey)
            val onTheAirTv = pardeService.getOnTheAirTv(apiKey)
            val popularTv = pardeService.getPopularTv(apiKey)
            val topRatedTv = pardeService.getTopRatedTv(apiKey)
            topRatedTvLiveData.postValue(topRatedTv.results)
            upcomingMovieLiveData.postValue(upcomingMovies.results)
            topRatedMovieLiveData.postValue(topRatedMovies.results)
            nowPlayingMovieLiveData.postValue(nowPlayingMovies.results)
            onTheAirTvLiveData.postValue(onTheAirTv.results)
            popularTvLiveData.postValue(popularTv.results)
        } catch (exception: Exception) {
            popularTvErrorLiveData.postValue("An error occurred: ${exception.message}")
            Log.d("thisisforrecyclertest","${exception.message}")
        }
    }
}