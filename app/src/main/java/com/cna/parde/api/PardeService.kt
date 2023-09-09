package com.cna.parde.api

import com.cna.parde.model.NPMoviesResponse
import com.cna.parde.model.OTATvResponse
import com.cna.parde.model.POPTvResponse
import com.cna.parde.model.TRMovieResponse
import com.cna.parde.model.TRTvResponse
import com.cna.parde.model.UcMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PardeService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("api_key") apikey: String): NPMoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("api_key") apikey: String): UcMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("api_key") apikey: String): TRMovieResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTv(@Query("api_key") apikey: String): OTATvResponse

    @GET("tv/popular")
    suspend fun getPopularTv(@Query("api_key") apikey: String): POPTvResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(@Query("api_key") apikey: String): TRTvResponse

}