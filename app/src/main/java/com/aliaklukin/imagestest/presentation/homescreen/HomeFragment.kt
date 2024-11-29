package com.aliaklukin.imagestest.presentation.homescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliaklukin.imagestest.databinding.FragmentHomeBinding
import com.aliaklukin.imagestest.presentation.base.BaseFragment
import com.aliaklukin.imagestest.presentation.homescreen.adapter.HitAdapter
import com.aliaklukin.imagestest.presentation.utils.visibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var hitAdapter: HitAdapter
    private val viewModel: HomeViewModel by viewModels()

    private var recyclerViewPosition = 0
    private var recyclerViewOffset = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { uiState ->
                    handleUiState(uiState)
                    val layoutManager = binding.hitsRV.layoutManager as? LinearLayoutManager
                    layoutManager?.scrollToPosition(uiState.scrollPosition)
                }
            }
        }

        initAdapter()
        initUI()
    }

    private fun initUI() {
        binding.run {
            hitsRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = hitAdapter
            }
        }
    }

    private fun initAdapter() {
        hitAdapter = HitAdapter { hit ->
            saveScrollPosition()
            val action = HomeFragmentDirections.actionHomeToImageInfo(hit)
            findNavController().navigate(action)
        }

        hitAdapter.onPageRequest = { page ->
            if (page > viewModel.state.value.currentPage) {
                viewModel.getHits(page)
            }
        }
    }

    private fun saveScrollPosition() {
        val layoutManager = binding.hitsRV.layoutManager as? LinearLayoutManager
        layoutManager?.let {
            recyclerViewPosition = it.findFirstVisibleItemPosition()
            val view = it.findViewByPosition(recyclerViewPosition)
            recyclerViewOffset = view?.top ?: 0
        }
    }

    private fun restoreScrollPosition() {
        val layoutManager = binding.hitsRV.layoutManager as? LinearLayoutManager
        layoutManager?.scrollToPositionWithOffset(recyclerViewPosition, recyclerViewOffset)
    }

    private fun handleUiState(uiState: HomeViewModel.HomeScreenState) {
        binding.run {
            progressV.visibility(uiState.isLoading)
            hitsRV.post {
                hitAdapter.addItems(uiState.hits)
                if (recyclerViewPosition != 0 || recyclerViewOffset != 0) {
                    restoreScrollPosition()
                }
            }
        }
    }
}