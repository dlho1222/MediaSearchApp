package com.part3.mediasearch.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.part3.mediasearch.SearchEvent
import com.part3.mediasearch.SearchUiState
import com.part3.mediasearch.SearchViewModel
import com.part3.mediasearch.databinding.FragmentSearchBinding
import com.part3.mediasearch.presentation.list.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        ListAdapter {
            viewModel.onClickFavorite(it)
        }
    }
    private val viewModel by activityViewModels<SearchViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        initView()
        initViewModel()
        return binding.root
    }

    private fun initViewModel() = with(viewModel) {
        lifecycleScope.launch {
            uiState.collectLatest { state ->
                onBind(state)
            }
        }
        lifecycleScope.launch {
            event.collectLatest { event ->
                onEvent(event)
            }
        }
    }

    private fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnClickFavorite -> viewModel.toggleFavorite(event.item)
        }
    }

    private fun onBind(state: SearchUiState) = with(binding) {
        recyclerView.isVisible = state.list.isNotEmpty()
        tvEmpty.isVisible = state.list.isEmpty()
        progressBar.isVisible = state.isLoading
        adapter.submitList(state.list)
    }

    private fun initView() = with(binding) {
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun search(query: String) {
        viewModel.search(query)
    }
}