package com.cna.parde

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 700
        fadeIn.fillAfter = true
        binding.introTextView.startAnimation(fadeIn)

        val intent = Intent(this,WelcomeActivity::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            startActivity(intent)
            finish()
        }
    }
}