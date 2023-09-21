package com.cna.parde.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cna.parde.adapters.CategoryViewPagerAdapter
import com.cna.parde.databinding.FragmentCategoriesBinding
import com.google.android.material.tabs.TabLayout

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        binding.tabLayoutc.addTab(binding.tabLayoutc.newTab().setText("Movie"))
        binding.tabLayoutc.addTab(binding.tabLayoutc.newTab().setText("Tv"))
        binding.tabLayoutc.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = CategoryViewPagerAdapter(
            requireActivity(),
            childFragmentManager,
            binding.tabLayoutc.tabCount
        )
        binding.viewPagerc.adapter = adapter
        binding.viewPagerc.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutc))
        binding.tabLayoutc.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPagerc.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}