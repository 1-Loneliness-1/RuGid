package com.rugid.feature.main.ui

import com.rugid.core.ui.BaseFragment
import com.rugid.feature.main.databinding.FragmentMainBinding

class MainFragment :
    BaseFragment<FragmentMainBinding, MainViewModel>(FragmentMainBinding::inflate) {

    override val viewModel by viewModel()

    override fun showLoadingState() {
        TODO("Not yet implemented")
    }

    override fun showContentState() {
        TODO("Not yet implemented")
    }

    override fun showErrorState() {
        TODO("Not yet implemented")
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }

    override fun initListeners() {
        TODO("Not yet implemented")
    }

}