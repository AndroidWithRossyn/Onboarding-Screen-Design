package com.rossyn.onboarding.design

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.rossyn.onboarding.design.databinding.LayoutScreenBinding

class ViewPagerAdapter(
    private val context: Context,
    private val mListScreen: List<ScreenItem>
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
