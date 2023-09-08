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
import com.cna.parde.model.OTATv
import com.cna.parde.model.TRMovie

class TvFragment : Fragment() {

    private lateinit var recyclerViewPopTv : RecyclerView

    private val otaTvAdapter by lazy {
        OTATvAdapter(object : OTATvAdapter.OTATvClickListener {
            override fun onOTATvClick(tv: OTATv) {
                openOTATvDetails(tv)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv, container, false)

        recyclerViewPopTv = view.findViewById(R.id.Recycler_tv_onTheAir)
        recyclerViewPopTv.adapter = otaTvAdapter

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



        return view
    }

    private fun openOTATvDetails(tv: OTATv) {
        Toast.makeText(requireContext(), tv.original_name, Toast.LENGTH_SHORT).show()
    }

}