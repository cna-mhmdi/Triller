package com.cna.parde.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cna.parde.fragments.AnimeFragment
import com.cna.parde.fragments.MovieFragment
import com.cna.parde.fragments.TvFragment

class ViewPagerAdapter(private val context: Context, fm: FragmentManager, private val totalTabs: Int)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                MovieFragment()
            }
            1 -> {
                TvFragment()
            }
            2 -> {
                AnimeFragment()
            }
            else -> getItem(position)
        }
    }


    override fun getCount(): Int {
        return totalTabs
    }
}