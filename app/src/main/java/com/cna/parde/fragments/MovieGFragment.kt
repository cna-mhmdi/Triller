package com.cna.parde.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.GMovieAdapter
import com.cna.parde.databinding.FragmentMovieGBinding
import com.cna.parde.detailActivity.DetailGMovieActivity
import com.cna.parde.detailActivity.DetailRecMovieActivity
import com.cna.parde.model.GMovie

class MovieGFragment : Fragment() {


    private var _binding: FragmentMovieGBinding? = null
    private val binding get() = _binding!!

    private val gMovieAdapter by lazy {
        GMovieAdapter(object : GMovieAdapter.GMovieClickListener {
            override fun onGMovieClick(movie: GMovie) {
                openGMovieDetails(movie)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMovieGBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.RecyclerMovieGenre.adapter = gMovieAdapter



        binding.GChipGroup.setOnCheckedChangeListener { _, checkedIds ->

            if (checkedIds != View.NO_ID) {
                when (checkedIds) {
                    R.id.cAction -> {
                        callingViewModel(28)
                    }

                    R.id.cAdventure -> {
                        callingViewModel(12)
                    }

                    R.id.cAnimation -> {
                        callingViewModel(16)
                    }

                    R.id.cComedy -> {
                        callingViewModel(35)
                    }

                    R.id.cCrime -> {
                        callingViewModel(80)
                    }

                    R.id.cDocumentary -> {
                        callingViewModel(99)
                    }

                    R.id.cDrama -> {
                        callingViewModel(18)
                    }

                    R.id.cFamily -> {
                        callingViewModel(10751)
                    }

                    R.id.cFantasy -> {
                        callingViewModel(14)
                    }

                    R.id.cHistory -> {
                        callingViewModel(36)
                    }

                    R.id.cHorror -> {
                        callingViewModel(27)
                    }

                    R.id.cMusic -> {
                        callingViewModel(10402)
                    }

                    R.id.cMystery -> {
                        callingViewModel(9648)
                    }

                    R.id.cRomance -> {
                        callingViewModel(10749)
                    }

                    R.id.cScienceFiction -> {
                        callingViewModel(878)
                    }

                    R.id.cTVMovie -> {
                        callingViewModel(10770)
                    }

                    R.id.cThriller -> {
                        callingViewModel(53)
                    }

                    R.id.cWar -> {
                        callingViewModel(10752)
                    }

                    R.id.cWestern -> {
                        callingViewModel(37)
                    }
                }
            }
        }

        return view
    }

    private fun callingViewModel(id: Int) {

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        pardeViewModel.setGenreId(id)
        pardeViewModel.genreMovie.observe(viewLifecycleOwner) { genreMovie ->
            gMovieAdapter.addMovies(genreMovie)
        }

        pardeViewModel.getGenreMovieError().observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGMovieDetails(movie: GMovie) {
        val intent = Intent(requireContext(), DetailGMovieActivity::class.java).apply {
            putExtra(DetailGMovieActivity.GMovie, movie)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}