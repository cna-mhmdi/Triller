package com.cna.parde

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.cna.parde.model.Cast
import com.cna.parde.model.CastTv
import com.cna.parde.model.GMovie
import com.cna.parde.model.GTv
import com.cna.parde.model.GenreMovie
import com.cna.parde.model.GenreTv
import com.cna.parde.model.NPMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPMovie
import com.cna.parde.model.POPTv
import com.cna.parde.model.RecMovie
import com.cna.parde.model.RecTv
import com.cna.parde.model.Search
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv
import com.cna.parde.model.UCMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PardeViewModel(private val pardeRepository: PardeRepository) : ViewModel() {

    private var genreMovieId: Int = 28
    private var genreTvId: Int = 35
    private var page: Int = 5
    private var userQuery: String = ""
    private var movieId: Int = 0
    private var movieIdCast: Int = 0
    private var movieSimilar: Int = 0
    private var tvDetail: Int = 0
    private var tvId: Int = 0

    fun setTvRec(path: Int) {
        tvId = path
        fetchRecTv()
    }

    fun setTvCast(path: Int) {
        tvId = path
        fetchCastTv()
    }

    fun setTvDetail(path: Int) {
        tvDetail = path
        fetchDetailTv()
    }

    fun setRecId(path: Int) {
        movieSimilar = path
        fetchRecMovie()
    }


    fun setCastId(path: Int) {
        movieIdCast = path
        fetchCastMovie()
    }

    fun setMovieId(path: Int) {
        movieId = path
        fetchMovieDetail()
    }

    fun setGenreId(id: Int) {
        genreMovieId = id
        getGenreMovies()
    }

    fun setGenreTvId(id: Int) {
        genreTvId = id
        getGenreTvs()
    }

    fun setSearch(query: String) {
        userQuery = query
        fetchSearch()
    }

    init {
        fetchMovies()
        fetchTvs()
    }

    val nowPlayingMovies: LiveData<List<NPMovie>> get() = pardeRepository.nowPlayingMovies
    fun getNowPlayingMovieError(): LiveData<String> = pardeRepository.nowPlayingMovieError

    val popularMovies: LiveData<List<POPMovie>> get() = pardeRepository.popularMovies
    fun getPopularMovieError(): LiveData<String> = pardeRepository.popularMovieError

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

    val trendingMovie: LiveData<List<TMovie>> get() = pardeRepository.trendingMovies
    fun getTrendingMovieError(): LiveData<String> = pardeRepository.trendingMovieError

    val trendingTv: LiveData<List<TTv>> get() = pardeRepository.trendingTv
    fun getTrendingTvError(): LiveData<String> = pardeRepository.trendingTvError

    val detailMovie: LiveData<List<GenreMovie>> get() = pardeRepository.detailMovie
    fun getDetailMovieError(): LiveData<String> = pardeRepository.detailMovieError

    val castMovie: LiveData<List<Cast>>
        get() = pardeRepository.castMovie.map { casts ->
            casts.sortedByDescending { it.popularity }
        }

    fun getCastMovieError(): LiveData<String> = pardeRepository.castMovieError

    val recMovie: LiveData<List<RecMovie>>
        get() = pardeRepository.recMovie.map { recMovie ->
            recMovie.sortedByDescending { it.vote_average }
        }

    fun getRecMovieError(): LiveData<String> = pardeRepository.recMovieError

    val detailTv: LiveData<List<GenreTv>> get() = pardeRepository.detailTv
    fun getDetailTvError(): LiveData<String> = pardeRepository.detailTvError

    val castTv: LiveData<List<CastTv>> get() = pardeRepository.castTv
    fun getCastTvError(): LiveData<String> = pardeRepository.castTvError

    val recTv: LiveData<List<RecTv>> get() = pardeRepository.recTv
    fun getRecTvError(): LiveData<String> = pardeRepository.recTvError

    private fun fetchRecTv(){
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchRecTv(tvId)
        }
    }

    private fun fetchCastTv(){
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchCastTv(tvId)
        }
    }

    private fun fetchDetailTv(){
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchDetailTv(tvDetail)
        }
    }

    private fun fetchRecMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchRecMovie(movieSimilar)
        }
    }

    private fun fetchCastMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchCastMovie(movieIdCast)
        }
    }

    private fun fetchMovieDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchDetailMovie(movieId)
        }
    }

    val genreMovie: LiveData<List<GMovie>>
        get() = pardeRepository.genreMovie.map { list ->
            list.sortedByDescending { it.vote_average }
        }

    fun getGenreMovieError(): LiveData<String> = pardeRepository.genreMovieError

    val genreTv: LiveData<List<GTv>>
        get() = pardeRepository.genreTv.map { list ->
            list.sortedByDescending { it.vote_average }
        }

    fun getGenreTvError(): LiveData<String> = pardeRepository.genreTvError

    val search: LiveData<List<Search>>
        get() = pardeRepository.search.map { list ->
            list.sortedByDescending { it.vote_average }
        }

    fun getSearchError(): LiveData<String> = pardeRepository.searchError

    private fun fetchSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchSearch(userQuery)
        }
    }

    private fun getGenreTvs() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchTvGenres(genreTvId, page)
        }
    }

    private fun getGenreMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchMovieGenres(genreMovieId, page)
        }
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchMovies()
        }
    }

    private fun fetchTvs() {
        viewModelScope.launch(Dispatchers.IO) {
            pardeRepository.fetchTvs()
        }
    }
}