package com.cna.parde.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cna.parde.fragments.MovieGFragment
import com.cna.parde.fragments.TvCFragment

class CategoryViewPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    private val totalTabs: Int,
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MovieGFragment()
            }

            1 -> {
                TvCFragment()
            }

            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}