package com.cna.parde

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.cna.parde.model.GMovie
import com.cna.parde.model.GTv
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

    private var genreMovieId : Int = 28
    private var genreTvId : Int = 35
    private var page: Int = 5

    fun setGenreId(id: Int){
        genreMovieId = id
        getGenreMovies()
    }

    fun setGenreTvId(id: Int){
        genreTvId = id
        getGenreTvs()
    }

    private fun getGenreTvs(){
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchTvGenres(genreTvId,page)
        }
    }

    private fun getGenreMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchMovieGenres(genreMovieId,page)
        }
    }

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

    val genreMovie: LiveData<List<GMovie>> get() = pardeRepository.genreMovie
    fun getGenreMovieError(): LiveData<String> = pardeRepository.genreMovieError

    val genreTv: LiveData<List<GTv>> get() = pardeRepository.genreTv
    fun getGenreTvError(): LiveData<String> = pardeRepository.genreTvError

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchMovies()
        }
    }


}