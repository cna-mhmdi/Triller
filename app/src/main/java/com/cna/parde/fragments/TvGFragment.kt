package com.cna.parde.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.R
import com.cna.parde.adapters.GTvAdapter
import com.cna.parde.databinding.FragmentMovieGBinding
import com.cna.parde.databinding.FragmentTvGBinding
import com.cna.parde.model.GMovie
import com.cna.parde.model.GTv

class TvGFragment : Fragment() {

    private var _binding : FragmentTvGBinding? = null
    private val binding get() = _binding!!

    private val gTvAdapter by lazy {
        GTvAdapter(object : GTvAdapter.GTvClickListener{
            override fun onGTvClick(tv: GTv) {
                openGTvDetails(tv)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTvGBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.RecyclerTvGenre.adapter = gTvAdapter

        binding.GChipGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != View.NO_ID){
                when(checkedId){
                    R.id.cActionAdventure ->{callingViewModel(10759)}
                    R.id.cAnimation ->{callingViewModel(16)}
                    R.id.cComedy ->{callingViewModel(35)}
                    R.id.cCrime ->{callingViewModel(80)}
                    R.id.cDocumentary ->{callingViewModel(99)}
                    R.id.cDrama ->{callingViewModel(18)}
                    R.id.cFamily ->{callingViewModel(10751)}
                    R.id.cKids ->{callingViewModel(10762)}
                    R.id.cMystery ->{callingViewModel(9648)}
                    R.id.cNews ->{callingViewModel(10763)}
                    R.id.cReality ->{callingViewModel(10764)}
                    R.id.cSciFiFantasy ->{callingViewModel(10765)}
                    R.id.cSoap ->{callingViewModel(10766)}
                    R.id.cTalk ->{callingViewModel(10767)}
                    R.id.cWarPolitics ->{callingViewModel(10768)}
                    R.id.cWestern ->{callingViewModel(37)}
                }
            }
        }


        return view
    }

    private fun callingViewModel(id:Int){

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        pardeViewModel.setGenreTvId(id)
        pardeViewModel.genreTv.observe(viewLifecycleOwner) { genreMovie->
            gTvAdapter.addMovies(genreMovie)
        }

        pardeViewModel.getGenreTvError().observe(viewLifecycleOwner) {error->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGTvDetails(tv: GTv) {
        Toast.makeText(requireContext(), tv.name, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}