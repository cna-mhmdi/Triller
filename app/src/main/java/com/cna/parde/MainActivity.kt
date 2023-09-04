package com.cna.parde

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.adapters.FYMovieAdapter
import com.cna.parde.adapters.OTAMovieAdapter
import com.cna.parde.adapters.UCMovieAdapter
import com.cna.parde.model.Movie

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG_USERNAME = "USERNAME"
        const val TAG_CHIP_NAMES = "CHIP_NAMES"

        val nameFY = arrayOf(
            "btn7",
            "btn8",
            "btn9",
            "btn10",
            "btn11",
            "btn12",
        )
        val ratesFY = arrayOf(
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
        )
        val nameUC = arrayOf(
            "btn13",
            "btn14",
            "btn15",
            "btn16",
            "btn17",
            "btn18",
            "btn19",
            "btn20",
        )
        val rateUC = arrayOf(
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
        )
    }

    private lateinit var recyclerViewOTAMovie : RecyclerView
//  private lateinit var adapterOTAMovie: OTAMovieAdapter

    private lateinit var recyclerViewFYMovie: RecyclerView
    private lateinit var adapterFYMovie: FYMovieAdapter

    private lateinit var recyclerViewUCMovie: RecyclerView
    private lateinit var adapterUCMovie: UCMovieAdapter

    private val otaMovieAdapter by lazy {
        OTAMovieAdapter(object : OTAMovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                openMovieDetails(movie)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewOTAMovie = findViewById(R.id.Recycler_OnTheAir)
        recyclerViewOTAMovie.adapter = otaMovieAdapter

        val pardeRepository = (application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        pardeViewModel.popularMovies.observe(this) { popularMovies ->
            otaMovieAdapter.addMovies(popularMovies)
        }
        pardeViewModel.getError().observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }

        recyclerViewFYMovie = findViewById(R.id.Recycler_ForYou)
        adapterFYMovie = FYMovieAdapter(nameFY,ratesFY)
        val layoutManagerFY = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recyclerViewFYMovie.layoutManager = layoutManagerFY
        recyclerViewFYMovie.adapter = adapterFYMovie

        recyclerViewUCMovie = findViewById(R.id.Recycler_UpComing)
        adapterUCMovie = UCMovieAdapter(nameUC,rateUC)
        val layoutManagerUC = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        recyclerViewUCMovie.layoutManager = layoutManagerUC
        recyclerViewUCMovie.adapter = adapterUCMovie

    }

    private fun openMovieDetails(movie: Movie) {
        Toast.makeText(this,movie.title,Toast.LENGTH_LONG).show()
    }
}