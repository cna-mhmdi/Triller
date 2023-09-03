package com.cna.parde.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.R
import com.cna.parde.adapters.FYMovieAdapter
import com.cna.parde.adapters.OTAMovieAdapter
import com.cna.parde.adapters.UCMovieAdapter

class MovieFragment : Fragment() {

    companion object{
        val namesOTA = arrayOf(
            "btn1",
            "btn2",
            "btn3",
            "btn4",
            "btn5",
            "btn6",
        )
        val ratesOTA = arrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
        )
        val nameFY = arrayOf(
            "btn7",
            "btn8",
            "btn9",
            "btn10",
            "btn11",
            "btn12",
        )
        val ratesFY = arrayOf(
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
        )
        val nameUC = arrayOf(
            "btn13",
            "btn14",
            "btn15",
            "btn16",
            "btn17",
            "btn18",
            "btn19",
            "btn20",
        )
        val rateUC = arrayOf(
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
        )
    }

    private lateinit var recyclerViewOTAMovie : RecyclerView
    private lateinit var adapterOTAMovie: OTAMovieAdapter

    private lateinit var recyclerViewFYMovie: RecyclerView
    private lateinit var adapterFYMovie: FYMovieAdapter

    private lateinit var recyclerViewUCMovie: RecyclerView
    private lateinit var adapterUCMovie: UCMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        recyclerViewOTAMovie = view.findViewById(R.id.Recycler_OnTheAir)
        adapterOTAMovie = OTAMovieAdapter(namesOTA, ratesOTA)
        val layoutManagerOTA = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewOTAMovie.layoutManager = layoutManagerOTA
        recyclerViewOTAMovie.adapter = adapterOTAMovie

        recyclerViewFYMovie = view.findViewById(R.id.Recycler_ForYou)
        adapterFYMovie = FYMovieAdapter(nameFY, ratesFY)
        val layoutManagerFY = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewFYMovie.layoutManager = layoutManagerFY
        recyclerViewFYMovie.adapter = adapterFYMovie

        recyclerViewUCMovie = view.findViewById(R.id.Recycler_UpComing)
        adapterUCMovie = UCMovieAdapter(nameUC, rateUC)
        val layoutManagerUC = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewUCMovie.layoutManager = layoutManagerUC
        recyclerViewUCMovie.adapter = adapterUCMovie

        return view
    }

}