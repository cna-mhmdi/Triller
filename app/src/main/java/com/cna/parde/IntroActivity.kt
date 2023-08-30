package com.cna.parde

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.cna.parde.databinding.ActivityIntroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private lateinit var introPreferences: SharedPreferences

    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        introPreferences = getSharedPreferences("introPreferences", Context.MODE_PRIVATE)
        val isIntroShowed = introPreferences.getBoolean("introPreferences",false)

        animation = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_in)
        binding.introTextView.startAnimation(animation)

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