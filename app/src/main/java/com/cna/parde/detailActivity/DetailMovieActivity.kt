package com.cna.parde.detailActivity

import android.app.ActivityOptions
import android.content.Intent
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
import com.cna.parde.adapters.RecMovieAdapter
import com.cna.parde.constant.Constant
import com.cna.parde.databinding.ActivityMovieDetailBinding
import com.cna.parde.model.Cast
import com.cna.parde.model.NPMovie
import com.cna.parde.model.POPMovie
import com.cna.parde.model.RecMovie
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val castMovieAdapter by lazy {
        CastMovieAdapter(object : CastMovieAdapter.CastMovieClickListener {
            override fun onCastMovieClick(cast: Cast) {
                openCastDetail(cast)
            }
        })
    }

    private val recMovieAdapter by lazy {
        RecMovieAdapter(object : RecMovieAdapter.RecMovieClickListener {
            override fun onRecMovieClick(movie: RecMovie) {
                openRecDetail(movie)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pardeRepository = (application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)


        binding.recyclerCastMovie.adapter = castMovieAdapter
        binding.recyclerRecMovie.adapter = recMovieAdapter

        val intent = intent
        if (intent != null) {

            val popMovie = intent.getParcelableExtra<POPMovie>(Constant.POPMovie)
            val npMovie = intent.getParcelableExtra<NPMovie>(Constant.NPMovie)
            val tMovie = intent.getParcelableExtra<TMovie>(Constant.TMovie)
            val trMovie = intent.getParcelableExtra<TRMovie>(Constant.TRMovie)
            val ucMovie = intent.getParcelableExtra<UCMovie>(Constant.UCMovie)

            if (popMovie != null) {

                Glide.with(this)
                    .load("$Constant.IMAGE_URL${popMovie.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = popMovie.title

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, popMovie.vote_average)


                pardeViewModel.setMovieId(popMovie.id)
                pardeViewModel.detailMovie.observe(this) { detail ->
                    binding.genreMovie.text = detail.map { it.name }.toString()
                }

                binding.moviePopularity.text = popMovie.popularity.toString()
                binding.movieMetaScore.text = popMovie.vote_count.toString()
                binding.movieOverview.text = popMovie.overview

                pardeViewModel.setCastId(popMovie.id)
                pardeViewModel.castMovie.observe(this) { cast ->
                    castMovieAdapter.addMovies(cast)
                }

                pardeViewModel.setRecId(popMovie.id)
                pardeViewModel.recMovie.observe(this) { recMovie ->
                    recMovieAdapter.addMovies(recMovie)
                }

            } else if (npMovie != null) {

                Glide.with(this)
                    .load("$Constant.IMAGE_URL${npMovie.poster_path}")
                    .placeholder(R.drawable.star)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = npMovie.title

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, npMovie.vote_average)


                pardeViewModel.setMovieId(npMovie.id)
                pardeViewModel.detailMovie.observe(this) { detail ->
                    binding.genreMovie.text = detail.map { it.name }.toString()
                }

                binding.moviePopularity.text = npMovie.popularity.toString()
                binding.movieMetaScore.text = npMovie.vote_count.toString()
                binding.movieOverview.text = npMovie.overview

                pardeViewModel.setCastId(npMovie.id)
                pardeViewModel.castMovie.observe(this) { cast ->
                    castMovieAdapter.addMovies(cast)
                }

                pardeViewModel.setRecId(npMovie.id)
                pardeViewModel.recMovie.observe(this) { recMovie ->
                    recMovieAdapter.addMovies(recMovie)
                }

            } else if (tMovie != null) {

                Glide.with(this)
                    .load("$Constant.IMAGE_URL${tMovie.poster_path}")
                    .placeholder(R.drawable.star)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = tMovie.title

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, tMovie.vote_average)


                pardeViewModel.setMovieId(tMovie.id)
                pardeViewModel.detailMovie.observe(this) { detail ->
                    binding.genreMovie.text = detail.map { it.name }.toString()
                }

                binding.moviePopularity.text = tMovie.popularity.toString()
                binding.movieMetaScore.text = tMovie.vote_count.toString()
                binding.movieOverview.text = tMovie.overview

                pardeViewModel.setCastId(tMovie.id)
                pardeViewModel.castMovie.observe(this) { cast ->
                    castMovieAdapter.addMovies(cast)
                }

                pardeViewModel.setRecId(tMovie.id)
                pardeViewModel.recMovie.observe(this) { recMovie ->
                    recMovieAdapter.addMovies(recMovie)
                }

            } else if (trMovie != null) {

                Glide.with(this)
                    .load("$Constant.IMAGE_URL${trMovie.poster_path}")
                    .placeholder(R.drawable.star)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = trMovie.title

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, trMovie.vote_average)


                pardeViewModel.setMovieId(trMovie.id)
                pardeViewModel.detailMovie.observe(this) { detail ->
                    binding.genreMovie.text = detail.map { it.name }.toString()
                }

                binding.moviePopularity.text = trMovie.popularity.toString()
                binding.movieMetaScore.text = trMovie.vote_count.toString()
                binding.movieOverview.text = trMovie.overview

                pardeViewModel.setCastId(trMovie.id)
                pardeViewModel.castMovie.observe(this) { cast ->
                    castMovieAdapter.addMovies(cast)
                }

                pardeViewModel.setRecId(trMovie.id)
                pardeViewModel.recMovie.observe(this) { recMovie ->
                    recMovieAdapter.addMovies(recMovie)
                }

            } else if (ucMovie != null) {

                Glide.with(this)
                    .load("$Constant.IMAGE_URL${ucMovie.poster_path}")
                    .placeholder(R.drawable.star)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = ucMovie.title

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, ucMovie.vote_average)


                pardeViewModel.setMovieId(ucMovie.id)
                pardeViewModel.detailMovie.observe(this) { detail ->
                    binding.genreMovie.text = detail.map { it.name }.toString()
                }

                binding.moviePopularity.text = ucMovie.popularity.toString()
                binding.movieMetaScore.text = ucMovie.vote_count.toString()
                binding.movieOverview.text = ucMovie.overview

                pardeViewModel.setCastId(ucMovie.id)
                pardeViewModel.castMovie.observe(this) { cast ->
                    castMovieAdapter.addMovies(cast)
                }

                pardeViewModel.setRecId(ucMovie.id)
                pardeViewModel.recMovie.observe(this) { recMovie ->
                    recMovieAdapter.addMovies(recMovie)
                }

            }
        }
    }

    private fun openCastDetail(cast: Cast) {
        Toast.makeText(this, cast.name, Toast.LENGTH_SHORT).show()
    }

    private fun openRecDetail(recMovie: RecMovie) {
        val intent = Intent(this, DetailRecMovieActivity::class.java).apply {
            putExtra(Constant.MOVIE, recMovie)
        }
        startActivity(
            intent, ActivityOptions
                .makeSceneTransitionAnimation(this).toBundle()
        )
    }
}