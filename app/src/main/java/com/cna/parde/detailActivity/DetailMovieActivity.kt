package com.cna.parde.detailActivity

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.CastMovieAdapter
import com.cna.parde.adapters.SimilarMovieAdapter
import com.cna.parde.databinding.ActivityMovieDetailBinding
import com.cna.parde.model.Cast
import com.cna.parde.model.NPMovie
import com.cna.parde.model.POPMovie
import com.cna.parde.model.SimilarMovie
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie

class DetailMovieActivity: AppCompatActivity() {

    companion object {
        const val POPMovie = "POPMovie"
        const val NPMovie = "NPMovie"
        const val TMovie = "TMovie"
        const val TRMovie = "TRMovie"
        const val UCMovie = "UCMovie"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    private lateinit var binding: ActivityMovieDetailBinding

    private val castMovieAdapter by lazy {
        CastMovieAdapter(object :CastMovieAdapter.CastMovieClickListener {
            override fun onCastMovieClick(cast: Cast) {
                openCastDetail(cast)
            }
        })
    }

    private val similarMovieAdapter by lazy {
        SimilarMovieAdapter(object :SimilarMovieAdapter.SimilarMovieClickListener {
            override fun onSimilarMovieClick(movie: SimilarMovie) {
                openSimilarDetail(movie)
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
        binding.recyclerSimilarMovie.adapter = similarMovieAdapter

        val intent = intent
        if(intent != null){
            val popMovie = intent.getParcelableExtra<POPMovie>(POPMovie)
            val npMovie = intent.getParcelableExtra<NPMovie>(NPMovie)
            val tMovie = intent.getParcelableExtra<TMovie>(TMovie)
            val trMovie = intent.getParcelableExtra<TRMovie>(TRMovie)
            val ucMovie = intent.getParcelableExtra<UCMovie>(UCMovie)

            if (popMovie != null){

                Glide.with(this)
                    .load("$IMAGE_URL${popMovie.poster_path}")
                    .placeholder(R.drawable.star)
                    .centerInside()
                    .into(binding.movieImg)

                binding.movieTitle.text = popMovie.title
                binding.rateMovie.text = "${popMovie.vote_average} /10 "

                pardeViewModel.setMovieId(popMovie.id)
                pardeViewModel.detailMovie.observe(this){ detail->
                    binding.genreMovie.text = detail.map { it.name}.toString()
                }

                binding.moviePopularity.text = popMovie.popularity.toString()
                binding.movieMetaScore.text = popMovie.vote_count.toString()
                binding.movieOverview.text = popMovie.overview

                pardeViewModel.setCastId(popMovie.id)
                pardeViewModel.castMovie.observe(this) {cast->
                    castMovieAdapter.addMovies(cast)
                }

                pardeViewModel.setSimilarId(popMovie.id)
                pardeViewModel.similarMovie.observe(this) {similarMovie->
                    similarMovieAdapter.addMovies(similarMovie)
                }

            }else if (npMovie != null) {
                binding.movieImg.load("$IMAGE_URL${npMovie.backdrop_path}")
            }else if (tMovie != null) {
                binding.movieImg.load("$IMAGE_URL${tMovie.backdrop_path}")
            }else if (trMovie != null) {
                binding.movieImg.load("$IMAGE_URL${trMovie.backdrop_path}")
            }else if (ucMovie != null) {
                binding.movieImg.load("$IMAGE_URL${ucMovie.backdrop_path}")
            }
        }
    }

    private fun openCastDetail(cast: Cast) {
        Toast.makeText(this,cast.name,Toast.LENGTH_SHORT).show()
    }

    private fun openSimilarDetail(similarMovie: SimilarMovie) {
        Toast.makeText(this,similarMovie.title,Toast.LENGTH_SHORT).show()
    }
}