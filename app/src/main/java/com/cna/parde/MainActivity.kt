package com.cna.parde

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cna.parde.adapters.FYMovieAdapter
import com.cna.parde.adapters.OTAMovieAdapter
import com.cna.parde.adapters.UCMovieAdapter
import com.cna.parde.model.PopularMovie
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG_USERNAME = "USERNAME"
        const val TAG_CHIP_NAMES = "CHIP_NAMES"

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.nav_view)?.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}