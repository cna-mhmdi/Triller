package com.cna.parde.fragments

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cna.parde.PardeApplication
import com.cna.parde.PardeViewModel
import com.cna.parde.adapters.SearchAdapter
import com.cna.parde.databinding.FragmentSearchBinding
import com.cna.parde.detailActivity.DetailSearchActivity
import com.cna.parde.model.Search

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchAdapter by lazy {
        SearchAdapter(object : SearchAdapter.SearchClickListener {
            override fun onSearchClick(movie: Search) {
                openSearchDetails(movie)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.RecyclerSearch.adapter = searchAdapter

        val pardeRepository = (activity?.application as PardeApplication).pardeRepository
        val pardeViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PardeViewModel(pardeRepository) as T
            }
        }).get(PardeViewModel::class.java)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    pardeViewModel.setSearch(query)
                    pardeViewModel.search.observe(viewLifecycleOwner) { search ->
                        searchAdapter.addMovies(search)
                    }

                    pardeViewModel.getSearchError().observe(viewLifecycleOwner) { error ->
                        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
                    }

                    binding.searchView.clearFocus()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "please Enter something first!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        return view
    }

    private fun openSearchDetails(movie: Search) {
        val intent = Intent(requireContext(), DetailSearchActivity::class.java).apply {
            putExtra(DetailSearchActivity.Search, movie)
        }
        startActivity(intent, ActivityOptions
            .makeSceneTransitionAnimation(requireContext() as Activity?).toBundle())
    }
}