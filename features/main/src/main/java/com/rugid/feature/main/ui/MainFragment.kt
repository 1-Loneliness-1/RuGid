package com.rugid.feature.main.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rugid.core.ui.BaseFragment
import com.rugid.core.ui.R.dimen.padding_8dp
import com.rugid.core.ui.navigation.AppNavigator
import com.rugid.feature.main.databinding.FragmentMainBinding
import com.rugid.feature.main.domain.model.MainData
import com.rugid.feature.main.ui.adapter.ArticleAdapter
import com.rugid.feature.main.ui.adapter.ExcursionAdapter
import com.rugid.feature.main.ui.adapter.PlaceAdapter
import com.rugid.feature.main.ui.adapter.VideoAdapter
import com.rugid.feature.main.ui.decoration.HorizontalSpaceItemDecoration
import com.rugid.feature.main.ui.model.MainUiEvent
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<FragmentMainBinding, MainData, MainViewModel>(FragmentMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModel()

    private val videoAdapter = VideoAdapter {}
    private val articleAdapter = ArticleAdapter {}
    private val placeAdapter = PlaceAdapter {}
    private val excursionAdapter = ExcursionAdapter {}

    fun RecyclerView.setHorizontalOrientation() {
        layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiEvents()
        if (savedInstanceState == null) {
            viewModel.getDataForMainScreen()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getDataForMainScreen()
    }

    override fun showLoadingState() {
        binding.nsvMainScreenView.visibility = View.GONE
        binding.pbMainScreen.visibility = View.VISIBLE
        binding.srlMainRefresh.isRefreshing = false
    }

    override fun showContentState(data: MainData) {
        bindLists(data)
        binding.pbMainScreen.visibility = View.GONE
        binding.nsvMainScreenView.visibility = View.VISIBLE
        binding.srlMainRefresh.isRefreshing = false
    }

    override fun showErrorState(error: Throwable) {
        binding.pbMainScreen.visibility = View.GONE
        binding.srlMainRefresh.isRefreshing = false
        Toast.makeText(
            requireContext(),
            error.localizedMessage ?: "Произошла какая-то не сетевая ошибка...",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun initViews() {

        binding.rvVideos.adapter = videoAdapter
        binding.rvArticles.adapter = articleAdapter
        binding.rvInterestingPlaces.adapter = placeAdapter
        binding.rvInterestingExcursions.adapter = excursionAdapter

        val space = resources.getDimensionPixelSize(padding_8dp)
        val horizontalSpaceItemDecoration = HorizontalSpaceItemDecoration(space)

        binding.rvVideos.addItemDecoration(horizontalSpaceItemDecoration)
        binding.rvArticles.addItemDecoration(horizontalSpaceItemDecoration)
        binding.rvInterestingPlaces.addItemDecoration(horizontalSpaceItemDecoration)
        binding.rvInterestingExcursions.addItemDecoration(horizontalSpaceItemDecoration)

        binding.rvVideos.setHorizontalOrientation()
        binding.rvArticles.setHorizontalOrientation()
        binding.rvInterestingPlaces.setHorizontalOrientation()
        binding.rvInterestingExcursions.setHorizontalOrientation()
    }

    override fun initListeners() {
        binding.srlMainRefresh.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeUiEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    handleUiEvent(event)
                }
            }
        }
    }

    private fun handleUiEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.OpenNetworkError -> {
                view?.post {
                    (requireActivity() as AppNavigator).openNetworkErrorFragment()
                }
            }
            is MainUiEvent.RefreshScreen -> {
                viewModel.getDataForMainScreen()
            }
        }
    }

    private fun bindLists(data: MainData) {
        videoAdapter.submitList(data.videos)
        articleAdapter.submitList(data.articles)
        placeAdapter.submitList(data.places)
        excursionAdapter.submitList(data.excursions)
    }

}