package com.cna.parde.detailActivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.CastMovieAdapter
import com.cna.parde.databinding.ActivityMovieGDetailBinding
import com.cna.parde.model.Cast
import com.cna.parde.model.GMovie

class DetailGMovieActivity : AppCompatActivity() {

    companion object {
        const val GMovie = "GMovie"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    private lateinit var binding: ActivityMovieGDetailBinding

    private val castMovieAdapter by lazy {
        CastMovieAdapter(object : CastMovieAdapter.CastMovieClickListener {
            override fun onCastMovieClick(cast: Cast) {
                openCastDetail(cast)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieGDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pardeRepository = (application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        binding.recyclerCastMovie.adapter = castMovieAdapter

        val intent = intent
        if (intent != null) {
            val gMovie = intent.getParcelableExtra<GMovie>(GMovie)

            if (gMovie !== null) {

                Glide.with(this)
                    .load("${IMAGE_URL}${gMovie.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = gMovie.title

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, gMovie.vote_average)


                pardeViewModel.setMovieId(gMovie.id)
                pardeViewModel.detailMovie.observe(this) { detail ->
                    binding.genreMovie.text = detail.map { it.name }.toString()
                }

                binding.moviePopularity.text = gMovie.popularity.toString()
                binding.movieMetaScore.text = gMovie.vote_count.toString()
                binding.movieOverview.text = gMovie.overview

                pardeViewModel.setCastId(gMovie.id)
                pardeViewModel.castMovie.observe(this) { cast ->
                    castMovieAdapter.addMovies(cast)
                }
            }
        }
    }

    private fun openCastDetail(cast: Cast) {
        Toast.makeText(this, cast.name, Toast.LENGTH_SHORT).show()
    }
}