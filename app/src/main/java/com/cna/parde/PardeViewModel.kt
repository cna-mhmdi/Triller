package com.cna.parde

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cna.parde.model.NPMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPMovie
import com.cna.parde.model.POPTv
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv
import com.cna.parde.model.UCMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PardeViewModel(private val pardeRepository: PardeRepository) : ViewModel() {

    init {
        fetchMovies()
    }

    val nowPlayingMovies: LiveData<List<NPMovie>> get() = pardeRepository.nowPlayingMovies
    fun getNowPlayingMovieError(): LiveData<String> = pardeRepository.nowPlayingMovieError

    val popularMovies:LiveData<List<POPMovie>> get() = pardeRepository.popularMovies
    fun getPopularMovieError():LiveData<String> = pardeRepository.popularMovieError

    val upComingMovie: LiveData<List<UCMovie>> get() = pardeRepository.upcomingMovies
    fun getUpComingMovieError(): LiveData<String> = pardeRepository.upcomingMovieError

    val topRatedMovie: LiveData<List<TRMovie>> get() = pardeRepository.topRatedMovies
    fun getTopRatedMovieError(): LiveData<String> = pardeRepository.topRatedMovieError

    val onTheAirTv: LiveData<List<OTATv>> get() = pardeRepository.onTheAirTv
    fun getOnTheAirTvError(): LiveData<String> = pardeRepository.onTheAirTvError

    val popularTv: LiveData<List<POPTv>> get() = pardeRepository.popularTv
    fun getPopularTvError(): LiveData<String> = pardeRepository.popularTvError

    val topRatedTv: LiveData<List<TRTv>> get() = pardeRepository.topRatedTv
    fun getTopRatedTvError(): LiveData<String> = pardeRepository.topRatedTvError

    val trendingMovie : LiveData<List<TMovie>> get() = pardeRepository.trendingMovies
    fun getTrendingMovieError(): LiveData<String> = pardeRepository.trendingMovieError

    val trendingTv : LiveData<List<TTv>> get() = pardeRepository.trendingTv
    fun getTrendingTvError(): LiveData<String> = pardeRepository.trendingTvError

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchMovies()
        }
    }
}