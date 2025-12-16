package com.rugid.feature.main.ui

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rugid.core.ui.BaseFragment
import com.rugid.feature.main.databinding.FragmentMainBinding
import com.rugid.feature.main.ui.adapter.ArticleAdapter
import com.rugid.feature.main.ui.adapter.ExcursionAdapter
import com.rugid.feature.main.ui.adapter.PlaceAdapter
import com.rugid.feature.main.ui.adapter.VideoAdapter
import com.rugid.feature.main.ui.decoration.HorizontalSpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<FragmentMainBinding, MainViewModel>(FragmentMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModel()

    override fun showLoadingState() {
        TODO("Not yet implemented")
    }

    override fun showContentState() {
        TODO("Not yet implemented")
    }

    override fun showErrorState() {
        Toast.makeText(requireContext(), "Произошла ошибка")
    }

    override fun initViews() {
        val videoAdapter = VideoAdapter {}
        val articleAdapter = ArticleAdapter {}
        val placeAdapter = PlaceAdapter {}
        val excursionAdapter = ExcursionAdapter {}

        binding.rvVideos.adapter = videoAdapter
        binding.rvArticles.adapter = articleAdapter
        binding.rvInterestingPlaces.adapter = placeAdapter
        binding.rvInterestingExcursions.adapter = excursionAdapter

        val space =
            resources.getDimensionPixelSize(HORIZONTAL_SPACE_FOR_RECYCLERVIEW_IN_DP)
        val horizontalSpaceItemDecoration = HorizontalSpaceItemDecoration(space)

        binding.rvVideos.addItemDecoration(horizontalSpaceItemDecoration)
        binding.rvArticles.addItemDecoration(horizontalSpaceItemDecoration)
        binding.rvInterestingPlaces.addItemDecoration(horizontalSpaceItemDecoration)
        binding.rvInterestingExcursions.addItemDecoration(horizontalSpaceItemDecoration)

        val layoutManagerForRecyclerViews = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvVideos.layoutManager = layoutManagerForRecyclerViews
        binding.rvArticles.layoutManager = layoutManagerForRecyclerViews
        binding.rvInterestingPlaces.layoutManager = layoutManagerForRecyclerViews
        binding.rvInterestingExcursions.layoutManager = layoutManagerForRecyclerViews
    }

    override fun initListeners() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val HORIZONTAL_SPACE_FOR_RECYCLERVIEW_IN_DP = 8
    }

}