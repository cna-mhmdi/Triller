package com.cna.parde

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"$userName$selectedChipNames",Toast.LENGTH_LONG).show()
    }
}