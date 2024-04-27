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
import com.cna.parde.adapters.CastAdapter
import com.cna.parde.adapters.RecAdapter
import com.cna.parde.constant.Constant
import com.cna.parde.databinding.ActivityMovieDetailBinding
import com.cna.parde.model.Cast
import com.cna.parde.model.NPMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPMovie
import com.cna.parde.model.POPTv
import com.cna.parde.model.Rec
import com.cna.parde.model.TRMovie
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv
import com.cna.parde.model.UCMovie

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val castAdapter by lazy {
        CastAdapter(object : CastAdapter.CastClickListener<Cast> {
            override fun onCastClick(cast: Cast) {
                openCastDetail(cast)
            }
        })
    }

    private val recAdapter by lazy {
        RecAdapter(object : RecAdapter.RecClickListener<Rec> {
            override fun onRecClick(movie: Rec) {
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

        binding.recyclerCastMovie.adapter = castAdapter
        binding.recyclerRecMovie.adapter = recAdapter

        val intent = intent
        if (intent != null) {
            val movie = when {
                intent.hasExtra(Constant.POPMovie) -> intent.getParcelableExtra<POPMovie>(Constant.POPMovie)
                intent.hasExtra(Constant.NPMovie) -> intent.getParcelableExtra<NPMovie>(Constant.NPMovie)
                intent.hasExtra(Constant.TRMovie) -> intent.getParcelableExtra<TRMovie>(Constant.TRMovie)
                intent.hasExtra(Constant.UCMovie) -> intent.getParcelableExtra<UCMovie>(Constant.UCMovie)
                intent.hasExtra(Constant.OTATv) -> intent.getParcelableExtra<OTATv>(Constant.OTATv)
                intent.hasExtra(Constant.POPTv) -> intent.getParcelableExtra<POPTv>(Constant.POPTv)
                intent.hasExtra(Constant.TRTv) -> intent.getParcelableExtra<TRTv>(Constant.TRTv)
                intent.hasExtra(Constant.TTv) -> intent.getParcelableExtra<TTv>(Constant.TTv)
                else -> null
            }

            movie?.let {
                Glide.with(this)
                    .load("${Constant.IMAGE_URL}${it.posterPath}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.movieImg)

                if (it.isMovie) {
                    binding.movieTitle.text = it.movieTitle
                    pardeViewModel.setCastId(it.movieId)
                    pardeViewModel.castMovie.observe(this) { cast ->
                        castAdapter.addMovies(cast)
                    }

                    pardeViewModel.setRecId(it.movieId)
                    pardeViewModel.recMovie.observe(this) { recMovie ->
                        recAdapter.addMovies(recMovie)
                    }

                    pardeViewModel.setMovieId(it.movieId)
                    pardeViewModel.detailMovie.observe(this) { detail ->
                        binding.genreMovie.text = detail.map { it.name }.toString()
                    }

                } else {
                    binding.movieTitle.text = it.movieName
                    pardeViewModel.setTvCast(it.movieId)
                    pardeViewModel.castTv.observe(this) { cast ->
                        castAdapter.addMovies(cast)
                    }

                    pardeViewModel.setTvRec(it.movieId)
                    pardeViewModel.recTv.observe(this) { recMovie ->
                        recAdapter.addMovies(recMovie)
                    }

                    pardeViewModel.setTvDetail(it.movieId)
                    pardeViewModel.detailTv.observe(this) { detail ->
                        binding.genreMovie.text = detail.map { it.name }.toString()
                    }
                }

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.rateMovie.text = String.format(voteAverageFormat, it.voteAverage)

                binding.moviePopularity.text = it.moviePopularity.toString()
                binding.movieMetaScore.text = it.voteCount.toString()
                binding.movieOverview.text = it.movieOverview


            }
        }
    }

    private fun openCastDetail(cast: Cast) {
        Toast.makeText(this, cast.name, Toast.LENGTH_SHORT).show()
    }

    private fun openRecDetail(recMovie: Rec) {
        val intent = Intent(this, DetailRecMovieActivity::class.java).apply {
            putExtra(Constant.MOVIE, recMovie)
        }
        startActivity(intent)
    }
}