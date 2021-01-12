package com.example.wingspanscores.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wingspanscores.R
import com.example.wingspanscores.history.HistoryFrag
import com.example.wingspanscores.input.InputFrag
import com.example.wingspanscores.list.ListFrag

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    val tabTitle = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_4
    )

    override fun getItemCount(): Int = tabTitle.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> ListFrag()
            2 -> HistoryFrag()
            else -> InputFrag()
        }
    }

}