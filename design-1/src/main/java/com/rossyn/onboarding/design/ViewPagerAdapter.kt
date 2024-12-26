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
package com.rossyn.onboarding.design

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.rossyn.onboarding.design.databinding.LayoutScreenBinding

/**
 * Custom PagerAdapter to handle the onboarding screens.
 * Manages the creation and destruction of screen views in the ViewPager.
 * @property context The context used for inflating layouts
 * @property mListScreen List of ScreenItems to display
 */
class ViewPagerAdapter(
    private val context: Context, private val mListScreen: List<ScreenItem>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = LayoutScreenBinding.inflate(LayoutInflater.from(context), container, false)

        binding.introTitle.text = mListScreen[position].title
        binding.introDescription.text = mListScreen[position].description
        binding.introImg.setImageResource(mListScreen[position].screenImg)

        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int = mListScreen.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
