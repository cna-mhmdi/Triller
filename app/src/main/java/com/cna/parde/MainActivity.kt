package com.cna.parde

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cna.parde.adapters.ViewPagerAdapter
import com.cna.parde.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG_USERNAME = "USERNAME"
        const val TAG_CHIP_NAMES = "CHIP_NAMES"
    }

    private val userName: String by lazy {
        intent.getStringExtra(TAG_USERNAME) ?:"مهمان"
    }

    //remember if list was empty
    private val selectedChipNames: List<String> by lazy {
        intent.getStringArrayListExtra(TAG_CHIP_NAMES) ?: emptyList()
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Movie"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tv"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Anime"))
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter =
            ViewPagerAdapter(this,supportFragmentManager,binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter

        binding.viewPager
            .addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab!!.position

            }
            override fun onTabUnselected(tab: TabLayout.Tab?){}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.txtUserName.text = userName
    }
}