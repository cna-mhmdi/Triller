package com.cna.parde.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.OTATvAdapter
import com.cna.parde.adapters.POPTvAdapter
import com.cna.parde.adapters.TRTvAdapter
import com.cna.parde.adapters.TTvAdapter
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPTv
import com.cna.parde.model.TRMovie
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv

class TvFragment : Fragment() {

    private lateinit var recyclerViewOntTv : RecyclerView
    private lateinit var recyclerViewPopTv : RecyclerView
    private lateinit var recyclerViewTrTv: RecyclerView
    private lateinit var recyclerViewTTv: RecyclerView

    private val otaTvAdapter by lazy {
        OTATvAdapter(object : OTATvAdapter.OTATvClickListener {
            override fun onOTATvClick(tv: OTATv) {
                openOTATvDetails(tv)
            }
        })
    }

    private val tTvAdapter by lazy {
        TTvAdapter(object : TTvAdapter.TTvClickListener {
            override fun onTTvClick(tv: TTv) {
                openTTvDetails(tv)
            }
        })
    }

    private val popTvAdapter by lazy {
        POPTvAdapter(object : POPTvAdapter.POPTvClickListener {
            override fun onPOPTvClick(tv: POPTv) {
                openPOPTvDetails(tv)
            }
        })
    }

    private val trTvAdapter by lazy {
        TRTvAdapter(object :TRTvAdapter.TRTvClickListener {
            override fun onTRTvClick(tv: TRTv) {
                openTRTvDetails(tv)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv, container, false)

        recyclerViewOntTv = view.findViewById(R.id.Recycler_tv_onTheAir)
        recyclerViewOntTv.adapter = otaTvAdapter

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        pardeViewModel.onTheAirTv.observe(viewLifecycleOwner) { onTheAirTv ->
            otaTvAdapter.addMovies(onTheAirTv)
        }

        pardeViewModel.getOnTheAirTvError().observe(viewLifecycleOwner) {error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewPopTv = view.findViewById(R.id.Recycler_tv_popular)
        recyclerViewPopTv.adapter = popTvAdapter

        pardeViewModel.popularTv.observe(viewLifecycleOwner) { popularTv->
            popTvAdapter.addMovies(popularTv)
        }

        pardeViewModel.getPopularTvError().observe(viewLifecycleOwner) { error->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        recyclerViewTrTv = view.findViewById(R.id.Recycler_tv_toprated)
        recyclerViewTrTv.adapter = trTvAdapter

        pardeViewModel.topRatedTv.observe(viewLifecycleOwner) {topRatedTv->
            trTvAdapter.addMovies(topRatedTv)
        }
        pardeViewModel.getTopRatedTvError().observe(viewLifecycleOwner) {error->
            Toast.makeText(requireContext(),error,Toast.LENGTH_SHORT).show()
        }

        recyclerViewTTv = view.findViewById(R.id.Recycler_tv_trending)
        recyclerViewTTv.adapter = tTvAdapter

        pardeViewModel.trendingTv.observe(viewLifecycleOwner) {trendingTv->
            tTvAdapter.addMovies(trendingTv)
        }

        pardeViewModel.getTrendingTvError().observe(viewLifecycleOwner) {error->
            Toast.makeText(requireContext(),error,Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun openOTATvDetails(tv: OTATv) {
        Toast.makeText(requireContext(), tv.original_name, Toast.LENGTH_SHORT).show()
    }

    private fun openPOPTvDetails(tv: POPTv) {
        Toast.makeText(requireContext(), tv.original_name, Toast.LENGTH_SHORT).show()
    }

    private fun openTRTvDetails(tv: TRTv) {
        Toast.makeText(requireContext(), tv.original_name, Toast.LENGTH_SHORT).show()
    }

    private fun openTTvDetails(tv: TTv) {
        Toast.makeText(requireContext(), tv.original_name, Toast.LENGTH_SHORT).show()
    }

}