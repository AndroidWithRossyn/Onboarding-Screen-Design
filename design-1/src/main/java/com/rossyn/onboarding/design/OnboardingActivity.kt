package com.rossyn.onboarding.design

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.rossyn.onboarding.design.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private lateinit var introViewPagerAdapter: ViewPagerAdapter
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if intro has been opened before
        if (restorePrefData()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }


        enableEdgeToEdge()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        // Setup views
        val btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)
        val mList = listOf(
            ScreenItem("Fresh Food", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit", R.drawable.img1),
            ScreenItem("Fast Delivery", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit", R.drawable.img2),
            ScreenItem("Easy Payment", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit", R.drawable.img3)
        )

        // Setup ViewPager
        introViewPagerAdapter = ViewPagerAdapter(this, mList)
        binding.screenViewpager.adapter = introViewPagerAdapter
        binding.tabIndicator.setupWithViewPager(binding.screenViewpager)

        // Next button click listener
        binding.btnNext.setOnClickListener {
            position = binding.screenViewpager.currentItem
            if (position < mList.size - 1) binding.screenViewpager.setCurrentItem(position + 1)
            if (position == mList.size - 1) loadLastScreen()
        }

        // TabIndicator listener
        binding.tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == mList.size - 1) loadLastScreen()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Get Started button listener
        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            savePrefsData()
            finish()
        }

        // Skip button listener
        binding.tvSkip.setOnClickListener {
            binding.screenViewpager.setCurrentItem(mList.size)
        }







    }

    private fun restorePrefData(): Boolean {
        val pref = getSharedPreferences("myPrefs", MODE_PRIVATE)
        return pref.getBoolean("isIntroOpnend", false)
    }

    private fun savePrefsData() {
        getSharedPreferences("myPrefs", MODE_PRIVATE).edit()
            .putBoolean("isIntroOpnend", true)
            .apply()
    }

    private fun loadLastScreen() {
        binding.apply {
            btnNext.visibility = View.INVISIBLE
            btnGetStarted.visibility = View.VISIBLE
            tvSkip.visibility = View.INVISIBLE
            tabIndicator.visibility = View.INVISIBLE
            btnGetStarted.animation = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)
        }
    }

}