package com.cna.parde

import android.app.Application
import com.cna.parde.api.MovieService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PardeApplication: Application() {

    lateinit var pardeRepository: PardeRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val movieService = retrofit.create(MovieService::class.java)

        pardeRepository = PardeRepository(movieService)
    }
}