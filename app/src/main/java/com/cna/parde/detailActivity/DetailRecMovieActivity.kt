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
import com.cna.parde.constant.Constant
import com.cna.parde.databinding.ActivityMovieRecDetailBinding
import com.cna.parde.model.Cast
import com.cna.parde.model.RecMovie

class DetailRecMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieRecDetailBinding

    private val castMovieAdapter by lazy {
        CastMovieAdapter(object : CastMovieAdapter.CastMovieClickListener {
            override fun onCastMovieClick(cast: Cast) {
                openCastMovie(cast)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieRecDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pardeRepository = (application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        binding.recyclerCastMovie.adapter = castMovieAdapter

        val intent = intent
        if (intent !== null) {
            val movieCast = intent.getParcelableExtra<RecMovie>(Constant.MOVIE)

            if (movieCast !== null) {

                Glide.with(this)
                    .load("${Constant.IMAGE_URL}${movieCast.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = movieCast.title

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, movieCast.vote_average)

                pardeViewModel.setMovieId(movieCast.id)
                pardeViewModel.detailTv.observe(this) { detailMovie ->
                    binding.genreMovie.text = detailMovie.map { it.name }.toString()

                }

                binding.moviePopularity.text = movieCast.popularity.toString()
                binding.movieMetaScore.text = movieCast.vote_count.toString()
                binding.movieOverview.text = movieCast.overview

                pardeViewModel.setCastId(movieCast.id)
                pardeViewModel.castMovie.observe(this) { castTv ->
                    castMovieAdapter.addMovies(castTv)
                }
            }
        }

    }

    private fun openCastMovie(movie: Cast) {
        Toast.makeText(this, movie.name, Toast.LENGTH_LONG).show()
    }
}