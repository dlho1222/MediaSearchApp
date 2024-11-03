package com.part3.mediasearch.presentation.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.part3.mediasearch.R
import com.part3.mediasearch.databinding.ActivitySearchBinding
import com.part3.mediasearch.presentation.list.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                val callBackFlow = callbackFlow {
                    val searchView = menu.findItem(R.id.option_menu).actionView as SearchView
                    val listener = object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String): Boolean {
                            binding.viewPager.setCurrentItem(0, true)
                            trySend(query)
                            return false
                        }

                        override fun onQueryTextChange(newText: String): Boolean {
                            trySend(newText)
                            return false
                        }
                    }
                    searchView.setOnQueryTextListener(listener)
                    awaitClose { (searchView.setOnQueryTextListener(null)) }
                }
                callBackFlow.collect { query ->
                    searchFragment.search(query)
                }
            }
        }
        return true
    }
}