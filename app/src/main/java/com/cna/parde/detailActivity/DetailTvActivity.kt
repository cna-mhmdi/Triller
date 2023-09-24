package com.cna.parde.detailActivity

import android.os.Bundle
import android.widget.TextView
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
import com.cna.parde.model.CastTv
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPTv
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val OTATv = "OTATv"
        const val TTv = "TTv"
        const val POPTv = "POPTv"
        const val TRTv = "TRTv"
        const val IMG_URL = "https://image.tmdb.org/t/p/w185/"
    }



    private lateinit var binding : ActivityTvDetailBinding

    private val castTvAdapter by lazy {
        CastTvAdapter(object : CastTvAdapter.CastTvClickListener {
            override fun onCastTvClick(tv: CastTv) {
                openCastTv(tv)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pardeRepository = (application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)


        val intent = intent
        if (intent != null) {
            val otaTv = intent.getParcelableExtra<OTATv>(OTATv)
            val tTv = intent.getParcelableExtra<TTv>(TTv)
            val popTv = intent.getParcelableExtra<POPTv>(POPTv)
            val trTv = intent.getParcelableExtra<TRTv>(TRTv)

            if (otaTv != null) {

                Glide.with(this)
                    .load("${DetailMovieActivity.IMAGE_URL}${otaTv.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.tvImg)

                binding.tvTitle.text = otaTv.name

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.ratetv.text = String.format(voteAverageFormat, otaTv.vote_average)

                pardeViewModel.setTvDetail(otaTv.id)
                pardeViewModel.detailTv.observe(this) { detailTv->
                    binding.genretv.text = detailTv.map { it.name }.toString()

                }

                binding.tvPopularity.text = otaTv.popularity.toString()
                binding.tvMetaScore.text = otaTv.vote_count.toString()
                binding.tvOverview.text = otaTv.overview

                pardeViewModel.setTvCast(otaTv.id)
                pardeViewModel.castTv.observe(this) {castTv->
                    castTvAdapter.addMovies(castTv)
                }
                pardeViewModel.getCastTvError().observe(this) {error->
                    Toast.makeText(this,error,Toast.LENGTH_LONG).show()
                }


            } else if (tTv != null) {

            } else if (popTv != null) {

            } else if (trTv != null) {

            }
        }
    }

    private fun openCastTv(tv: CastTv){
        Toast.makeText(this,tv.name,Toast.LENGTH_LONG).show()
    }
}