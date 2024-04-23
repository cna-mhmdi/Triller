package com.cna.parde.api

import com.cna.parde.model.CastResponse
import com.cna.parde.model.DetailMovie
import com.cna.parde.model.DetailTvResponse
import com.cna.parde.model.GMovieResponse
import com.cna.parde.model.GTvResponse
import com.cna.parde.model.NPMoviesResponse
import com.cna.parde.model.OTATvResponse
import com.cna.parde.model.POPMoviesResponse
import com.cna.parde.model.POPTvResponse
import com.cna.parde.model.RecResponse
import com.cna.parde.model.SearchResponse
import com.cna.parde.model.TRMovieResponse
import com.cna.parde.model.TRTvResponse
import com.cna.parde.model.TTvResponse
import com.cna.parde.model.UcMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PardeService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("api_key") apikey: String): NPMoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apikey: String): POPMoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("api_key") apikey: String): UcMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("api_key") apikey: String): TRMovieResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTv(@Query("api_key") apikey: String): OTATvResponse

    @GET("trending/tv/week")
    suspend fun getTrendingTv(@Query("api_key") apikey: String): TTvResponse

    @GET("tv/popular")
    suspend fun getPopularTv(@Query("api_key") apikey: String): POPTvResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(@Query("api_key") apikey: String): TRTvResponse


    @GET("discover/movie")
    suspend fun getGenreMovie(
        @Query("api_key") apikey: String,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,
    ): GMovieResponse

    @GET("discover/tv")
    suspend fun getGenreTv(
        @Query("api_key") apikey: String,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,
    ): GTvResponse

    @GET("search/multi")
    suspend fun getSearch(
        @Query("api_key") apikey: String,
        @Query("query") query: String,
    ): SearchResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): DetailMovie

    @GET("movie/{movie_id}/credits")
    suspend fun getCastMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apikey: String,
    ): CastResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apikey: String,
    ): RecResponse

    @GET("tv/{series_id}")
    suspend fun getTvDetail(
        @Path("series_id") seriesId: Int,
        @Query("api_key") apiKey: String,
    ): DetailTvResponse

    @GET("tv/{series_id}/aggregate_credits")
    suspend fun getCastTv(
        @Path("series_id") seriesId: Int,
        @Query("api_key") apikey: String,
    ): CastResponse


    @GET("tv/{series_id}/recommendations")
    suspend fun getRecTv(
        @Path("series_id") seriesId: Int,
        @Query("api_key") apikey: String,
    ): RecResponse

}