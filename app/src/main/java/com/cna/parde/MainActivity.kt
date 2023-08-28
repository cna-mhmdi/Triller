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



        binding.txtUserName.text = userName
    }
}