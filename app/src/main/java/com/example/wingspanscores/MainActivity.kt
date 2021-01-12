package com.example.wingspanscores

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wingspanscores.databinding.ActivityMainBinding
import com.example.wingspanscores.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SectionsPagerAdapter(this)
        binding.apply {
            viewPager.adapter = adapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = getString(adapter.tabTitle[position])
            }.attach()
        }
    }

    fun toPlayerFrag() {
        binding.viewPager.currentItem = 1
    }
}

