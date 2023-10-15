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
import com.cna.parde.adapters.CastTvAdapter
import com.cna.parde.adapters.RecTvAdapter
import com.cna.parde.constant.Constant
import com.cna.parde.databinding.ActivityTvDetailBinding
import com.cna.parde.model.CastTv
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPTv
import com.cna.parde.model.RecTv
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv

class DetailTvActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvDetailBinding

    private val castTvAdapter by lazy {
        CastTvAdapter(object : CastTvAdapter.CastTvClickListener {
            override fun onCastTvClick(tv: CastTv) {
                openCastTv(tv)
            }
        })
    }

    private val recTvAdapter by lazy {
        RecTvAdapter(object : RecTvAdapter.RecTvClickListener {
            override fun onRecTvClick(tv: RecTv) {
                openRecTv(tv)
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

        binding.recyclerCastTv.adapter = castTvAdapter
        binding.recyclerRecTv.adapter = recTvAdapter


        val intent = intent
        if (intent != null) {
            val otaTv = intent.getParcelableExtra<OTATv>(Constant.OTATv)
            val tTv = intent.getParcelableExtra<TTv>(Constant.TTv)
            val popTv = intent.getParcelableExtra<POPTv>(Constant.POPTv)
            val trTv = intent.getParcelableExtra<TRTv>(Constant.TRTv)

            if (otaTv != null) {

                Glide.with(this)
                    .load("${Constant.IMAGE_URL}${otaTv.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.tvImg)

                binding.tvTitle.text = otaTv.name

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.ratetv.text = String.format(voteAverageFormat, otaTv.vote_average)

                pardeViewModel.setTvDetail(otaTv.id)
                pardeViewModel.detailTv.observe(this) { detailTv ->
                    binding.genretv.text = detailTv.map { it.name }.toString()

                }

                binding.tvPopularity.text = otaTv.popularity.toString()
                binding.tvMetaScore.text = otaTv.vote_count.toString()
                binding.tvOverview.text = otaTv.overview

                pardeViewModel.setTvCast(otaTv.id)
                pardeViewModel.castTv.observe(this) { castTv ->
                    castTvAdapter.addMovies(castTv)
                }
                pardeViewModel.getCastTvError().observe(this) { error ->
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
                pardeViewModel.setTvRec(otaTv.id)
                pardeViewModel.recTv.observe(this) { recTv ->
                    recTvAdapter.addMovies(recTv)
                }


            } else if (tTv != null) {

                Glide.with(this)
                    .load("${Constant.IMAGE_URL}${tTv.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.tvImg)

                binding.tvTitle.text = tTv.name

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.ratetv.text = String.format(voteAverageFormat, tTv.vote_average)

                pardeViewModel.setTvDetail(tTv.id)
                pardeViewModel.detailTv.observe(this) { detailTv ->
                    binding.genretv.text = detailTv.map { it.name }.toString()

                }

                binding.tvPopularity.text = tTv.popularity.toString()
                binding.tvMetaScore.text = tTv.vote_count.toString()
                binding.tvOverview.text = tTv.overview

                pardeViewModel.setTvCast(tTv.id)
                pardeViewModel.castTv.observe(this) { castTv ->
                    castTvAdapter.addMovies(castTv)
                }
                pardeViewModel.getCastTvError().observe(this) { error ->
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
                pardeViewModel.setTvRec(tTv.id)
                pardeViewModel.recTv.observe(this) { recTv ->
                    recTvAdapter.addMovies(recTv)
                }

            } else if (popTv != null) {

                Glide.with(this)
                    .load("${Constant.IMAGE_URL}${popTv.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.tvImg)

                binding.tvTitle.text = popTv.name

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.ratetv.text = String.format(voteAverageFormat, popTv.vote_average)

                pardeViewModel.setTvDetail(popTv.id)
                pardeViewModel.detailTv.observe(this) { detailTv ->
                    binding.genretv.text = detailTv.map { it.name }.toString()

                }

                binding.tvPopularity.text = popTv.popularity.toString()
                binding.tvMetaScore.text = popTv.vote_count.toString()
                binding.tvOverview.text = popTv.overview

                pardeViewModel.setTvCast(popTv.id)
                pardeViewModel.castTv.observe(this) { castTv ->
                    castTvAdapter.addMovies(castTv)
                }
                pardeViewModel.getCastTvError().observe(this) { error ->
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
                pardeViewModel.setTvRec(popTv.id)
                pardeViewModel.recTv.observe(this) { recTv ->
                    recTvAdapter.addMovies(recTv)
                }

            } else if (trTv != null) {

                Glide.with(this)
                    .load("${Constant.IMAGE_URL}${trTv.poster_path}")
                    .placeholder(R.drawable.placeholder)
                    .centerInside()
                    .into(binding.tvImg)

                binding.tvTitle.text = trTv.name

                val voteAverageFormat = getString(R.string.vote_average_format)
                binding.ratetv.text = String.format(voteAverageFormat, trTv.vote_average)

                pardeViewModel.setTvDetail(trTv.id)
                pardeViewModel.detailTv.observe(this) { detailTv ->
                    binding.genretv.text = detailTv.map { it.name }.toString()

                }

                binding.tvPopularity.text = trTv.popularity.toString()
                binding.tvMetaScore.text = trTv.vote_count.toString()
                binding.tvOverview.text = trTv.overview

                pardeViewModel.setTvCast(trTv.id)
                pardeViewModel.castTv.observe(this) { castTv ->
                    castTvAdapter.addMovies(castTv)
                }
                pardeViewModel.getCastTvError().observe(this) { error ->
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                }
                pardeViewModel.setTvRec(trTv.id)
                pardeViewModel.recTv.observe(this) { recTv ->
                    recTvAdapter.addMovies(recTv)
                }

            }
        }
    }

    private fun openCastTv(tv: CastTv) {
        Toast.makeText(this, tv.name, Toast.LENGTH_LONG).show()
    }

    private fun openRecTv(tv: RecTv) {
        val intent = Intent(this, DetailRecTvActivity::class.java).apply {
            putExtra(Constant.TV, tv)
        }
        startActivity(intent, ActivityOptions
            .makeSceneTransitionAnimation(this).toBundle())
    }
}