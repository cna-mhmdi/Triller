package com.cna.parde.api

import com.cna.parde.model.NPMoviesResponse
import com.cna.parde.model.TRMovieResponse
import com.cna.parde.model.UcMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("api_key") apikey: String): NPMoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("api_key") apikey: String): UcMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("api_key") apikey: String): TRMovieResponse

}