package com.cna.parde.model

data class PopularMoviesResponse(
    val page : Int,
    val results: List<Movie>
)
