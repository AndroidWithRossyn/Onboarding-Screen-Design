/**
 * Copyright (c) 2024. Rossyn
 * <p>
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rossyn.onboarding.design2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rossyn.onboarding.design2.databinding.ActivityOnboardingBinding
import kotlin.compareTo

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private val layouts = intArrayOf(
        R.layout.screen_1, R.layout.screen_2, R.layout.screen_3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pagerAdapter = ScreenSlidePagerAdapter(layouts)
        binding.aoViewpager.adapter = pagerAdapter

        // Set up WormDotsIndicator
        binding.aoIndicator.attachTo(binding.aoViewpager)

        binding.aoViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == (layouts.size - 1)) {
                    binding.aoSkip.visibility = View.INVISIBLE
                    binding.aoNext.visibility = View.INVISIBLE
                    binding.aoStart.visibility = View.VISIBLE
                } else {
                    binding.aoSkip.visibility = View.VISIBLE
                    binding.aoNext.visibility = View.VISIBLE
                    binding.aoStart.visibility = View.INVISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })


        binding.aoNext.setOnClickListener {
            val current = binding.aoViewpager.currentItem + 1
            if (current < layouts.size) {
                binding.aoViewpager.currentItem = current
            }
        }

        binding.aoSkip.setOnClickListener {
            binding.aoViewpager.currentItem = layouts.size - 1
        }
        binding.aoStart.setOnClickListener {
            homeScreen()
        }

    }

    private fun homeScreen() {
        val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private inner class ScreenSlidePagerAdapter(private val layouts: IntArray) :
        RecyclerView.Adapter<ScreenSlidePagerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        }

        override fun getItemCount(): Int = layouts.size

        override fun getItemViewType(position: Int): Int = layouts[position]

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}