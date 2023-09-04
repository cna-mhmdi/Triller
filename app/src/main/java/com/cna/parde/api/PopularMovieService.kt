package com.cna.parde.api

import com.cna.parde.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMovieService {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apikey : String): PopularMoviesResponse

}