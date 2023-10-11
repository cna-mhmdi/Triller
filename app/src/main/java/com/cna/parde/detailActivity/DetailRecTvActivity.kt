package com.cna.parde.detailActivity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.CastTvAdapter
import com.cna.parde.databinding.ActivityTvDetailBinding
import com.cna.parde.databinding.ActivityTvRecDetailBinding
import com.cna.parde.model.CastTv
import com.cna.parde.model.RecTv

class DetailRecTvActivity:AppCompatActivity() {

    companion object {
        const val TV = "TV"
        const val IMG_URL = "https://image.tmdb.org/t/p/w185/"
    }

    private lateinit var binding : ActivityTvRecDetailBinding

    private val castTvAdapter by lazy {
        CastTvAdapter(object : CastTvAdapter.CastTvClickListener {
            override fun onCastTvClick(tv: CastTv) {
                openCastTv(tv)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvRecDetailBinding.inflate(layoutInflater)
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
            val tv = intent.getParcelableExtra<RecTv>(TV)

            if (tv !== null) {

                Glide.with(this)
                    .load("${IMG_URL}${tv.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.tvImg)

                binding.tvTitle.text = tv.name

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.ratetv.text = String.format(voteAverageFormat, tv.vote_average)

                pardeViewModel.setTvDetail(tv.id)
                pardeViewModel.detailTv.observe(this) { detailTv->
                    binding.genretv.text = detailTv.map { it.name }.toString()

                }

                binding.tvPopularity.text = tv.popularity.toString()
                binding.tvMetaScore.text = tv.vote_count.toString()
                binding.tvOverview.text = tv.overview

                pardeViewModel.setTvCast(tv.id)
                pardeViewModel.castTv.observe(this) {castTv->
                    castTvAdapter.addMovies(castTv)
                }

            }
        }
    }

    private fun openCastTv(tv: CastTv){
        Toast.makeText(this,tv.name,Toast.LENGTH_LONG).show()
    }
}