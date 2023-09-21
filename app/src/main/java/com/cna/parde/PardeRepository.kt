package com.cna.parde

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cna.parde.api.PardeService
import com.cna.parde.model.DetailMovie
import com.cna.parde.model.GMovie
import com.cna.parde.model.GTv
import com.cna.parde.model.GenreMovie
import com.cna.parde.model.NPMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPMovie
import com.cna.parde.model.POPTv
import com.cna.parde.model.Search
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv
import com.cna.parde.model.UCMovie

class PardeRepository(private val pardeService: PardeService) {

    private val apiKey = "721e4f18664869b21649e0be3e99ec59"

    private val nowPlayingMovieLiveData = MutableLiveData<List<NPMovie>>()
    private val nowPlayingMovieErrorLiveData = MutableLiveData<String>()

    val nowPlayingMovies: LiveData<List<NPMovie>>
        get() = nowPlayingMovieLiveData

    val nowPlayingMovieError: LiveData<String>
        get() = nowPlayingMovieErrorLiveData

    private val trendingMovieLiveData = MutableLiveData<List<TMovie>>()
    private val trendingMovieErrorLiveData = MutableLiveData<String>()

    val trendingMovies: LiveData<List<TMovie>>
        get() = trendingMovieLiveData

    val trendingMovieError: LiveData<String>
        get() = trendingMovieErrorLiveData

    private val popularMovieLiveData = MutableLiveData<List<POPMovie>>()
    private val popularMovieErrorLiveData = MutableLiveData<String>()

    val popularMovies: LiveData<List<POPMovie>>
        get() = popularMovieLiveData

    val popularMovieError: LiveData<String>
        get() = popularMovieErrorLiveData

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

    private val trendingTvLiveData = MutableLiveData<List<TTv>>()
    private val trendingTvErrorLiveData = MutableLiveData<String>()

    val trendingTv: LiveData<List<TTv>>
        get() = trendingTvLiveData
    val trendingTvError: LiveData<String>
        get() = trendingTvErrorLiveData

    private val genresMovieLiveData = MutableLiveData<List<GMovie>>()
    private val genresMovieErrorLiveData = MutableLiveData<String>()

    val genreMovie: LiveData<List<GMovie>>
        get() = genresMovieLiveData
    val genreMovieError: LiveData<String>
        get() = genresMovieErrorLiveData

    private val genresTvLiveData = MutableLiveData<List<GTv>>()
    private val genresTvErrorLiveData = MutableLiveData<String>()

    val genreTv: LiveData<List<GTv>>
        get() = genresTvLiveData
    val genreTvError: LiveData<String>
        get() = genresTvErrorLiveData

    private val searchLiveData = MutableLiveData<List<Search>>()
    private val searchErrorLiveData = MutableLiveData<String>()

    val search : LiveData<List<Search>> get() = searchLiveData
    val searchError: LiveData<String> get() = searchErrorLiveData


    private val detailMovieLiveData = MutableLiveData<List<GenreMovie>>()
    private val detailMovieErrorLiveData = MutableLiveData<String>()

    val detailMovie : LiveData<List<GenreMovie>> get() = detailMovieLiveData
    val detailMovieError: LiveData<String> get() = detailMovieErrorLiveData

    private val taglineMovieLiveData = MutableLiveData<List<DetailMovie>>()
    private val taglineMovieErrorLiveData = MutableLiveData<String>()

    suspend fun fetchDetailMovie(path:Int) {
        val detailMovie = pardeService.getDetailMovie(path,apiKey)
        detailMovieLiveData.postValue(detailMovie.genres)
    }

    suspend fun fetchSearch(query: String) {
        val search = pardeService.getSearch(apiKey,query)
        searchLiveData.postValue(search.results)
    }

    suspend fun fetchMovieGenres(genreId: Int,pages: Int) {
        val genreMovies = mutableListOf<GMovie>()
        for (i in 1..pages) {
            val response = pardeService.getGenreMovie(apiKey, genreId, i)
            genreMovies.addAll(response.results)
        }
        genresMovieLiveData.postValue(genreMovies)
    }

    suspend fun fetchTvGenres(genreId: Int,pages: Int) {
        val genreTv = mutableListOf<GTv>()
        for (i in 1..pages){
            val response = pardeService.getGenreTv(apiKey,genreId,i)
            genreTv.addAll(response.results)
        }
        genresTvLiveData.postValue(genreTv)
    }

    suspend fun fetchMovies() {
        try {
            val nowPlayingMovies = pardeService.getNowPlayingMovie(apiKey)
            val popularMovies = pardeService.getPopularMovie(apiKey)
            val upcomingMovies = pardeService.getUpcomingMovie(apiKey)
            val topRatedMovies = pardeService.getTopRatedMovie(apiKey)
            val trendingMovie = pardeService.getTrendingMovie(apiKey)
            val onTheAirTv = pardeService.getOnTheAirTv(apiKey)
            val popularTv = pardeService.getPopularTv(apiKey)
            val topRatedTv = pardeService.getTopRatedTv(apiKey)
            val trendingTv = pardeService.getTrendingTv(apiKey)
            trendingTvLiveData.postValue(trendingTv.results)
            trendingMovieLiveData.postValue(trendingMovie.results)
            popularMovieLiveData.postValue(popularMovies.results)
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