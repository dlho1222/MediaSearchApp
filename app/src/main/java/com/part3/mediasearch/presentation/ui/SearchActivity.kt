package com.part3.mediasearch.presentation.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.part3.mediasearch.R
import com.part3.mediasearch.databinding.ActivitySearchBinding
import com.part3.mediasearch.presentation.list.ViewPagerAdapter

class SearchActivity : AppCompatActivity() {
    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(
            layoutInflater
        )
    }
    private val searchFragment by lazy { SearchFragment() }
    private val fragmentList by lazy { listOf(searchFragment, FavoritesFragment()) }
    private val adapter by lazy {
        ViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            fragmentList
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.search_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
    }

    private fun initView() = with(binding) {
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (fragmentList[position] is SearchFragment) "검색 결과" else "즐겨찾기"
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }
}