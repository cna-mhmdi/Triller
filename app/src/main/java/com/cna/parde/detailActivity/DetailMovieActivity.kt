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
import com.cna.parde.databinding.ActivityMovieDetailBinding
import com.cna.parde.model.NPMovie
import com.cna.parde.model.POPMovie
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        if(intent != null){
            val popMovie = intent.getParcelableExtra<POPMovie>(POPMovie)
            val npMovie = intent.getParcelableExtra<NPMovie>(NPMovie)
            val tMovie = intent.getParcelableExtra<TMovie>(TMovie)
            val trMovie = intent.getParcelableExtra<TRMovie>(TRMovie)
            val ucMovie = intent.getParcelableExtra<UCMovie>(UCMovie)

            if (popMovie != null){

                binding.movieTitle.text = popMovie.title
                binding.moviePopularity.text = popMovie.popularity.toString()
                binding.movieMetaScore.text = popMovie.vote_count.toString()
                binding.movieOverview.text = popMovie.overview

                binding.rateMovie.text = "${popMovie.vote_average} /10 "

                Glide.with(this)
                    .load("$IMAGE_URL${popMovie.poster_path}")
                    .placeholder(R.drawable.star)
                    .centerInside()
                    .into(binding.movieImg)

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
}