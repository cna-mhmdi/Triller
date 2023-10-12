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
import com.cna.parde.adapters.CastTvAdapter
import com.cna.parde.databinding.ActivityTvGDetailBinding
import com.cna.parde.model.CastTv
import com.cna.parde.model.GTv

class DetailGTvActivity : AppCompatActivity() {

    companion object {
        const val GTv = "GTv"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    private lateinit var binding: ActivityTvGDetailBinding

    private val castTvAdapter by lazy {
        CastTvAdapter(object : CastTvAdapter.CastTvClickListener {
            override fun onCastTvClick(tv: CastTv) {
                openCastTv(tv)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvGDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pardeRepository = (application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        binding.recyclerCastTv.adapter = castTvAdapter

        val intent = intent
        if (intent !== null) {
            val castTv = intent.getParcelableExtra<GTv>(GTv)

            if (castTv !== null) {

                Glide.with(this)
                    .load("${IMAGE_URL}${castTv.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.tvImg)

                binding.tvTitle.text = castTv.name

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.ratetv.text = String.format(voteAverageFormat, castTv.vote_average)

                pardeViewModel.setTvDetail(castTv.id)
                pardeViewModel.detailTv.observe(this) { detailTv ->
                    binding.genretv.text = detailTv.map { it.name }.toString()

                }

                binding.tvPopularity.text = castTv.popularity.toString()
                binding.tvMetaScore.text = castTv.vote_count.toString()
                binding.tvOverview.text = castTv.overview

                pardeViewModel.setTvCast(castTv.id)
                pardeViewModel.castTv.observe(this) { castTv ->
                    castTvAdapter.addMovies(castTv)
                }
                pardeViewModel.getCastTvError().observe(this) { error ->
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun openCastTv(tv: CastTv) {
        Toast.makeText(this, tv.name, Toast.LENGTH_LONG).show()
    }
}