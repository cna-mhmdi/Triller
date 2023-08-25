package com.cna.parde

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import com.cna.parde.databinding.ActivityIntroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private lateinit var introPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        introPreferences = getSharedPreferences("introPreferences", Context.MODE_PRIVATE)
        val isIntroShowed = introPreferences.getBoolean("introPreferences",false)

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 700
        fadeIn.fillAfter = true
        binding.introTextView.startAnimation(fadeIn)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)

            val targetActivity = if (!isIntroShowed) {

                val editor = introPreferences.edit()
                editor.putBoolean("introPreferences",true)
                editor.apply()
                WelcomeActivity::class.java

            } else {
                MainActivity::class.java
            }
            startActivity(Intent(this@IntroActivity,targetActivity))

            finish()
        }
    }
}