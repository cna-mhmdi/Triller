package com.cna.parde.api

import com.cna.parde.model.PopularMoviesResponse
import com.cna.parde.model.UcMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apikey: String): PopularMoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("api_key") apikey: String): UcMovieResponse

}