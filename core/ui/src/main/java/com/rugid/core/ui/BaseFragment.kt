package com.rugid.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.rugid.core.model.DataState

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel<*>>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    private val binding get() = _binding!!

    abstract val viewModel: VM

    abstract fun showLoadingState()
    abstract fun showContentState()
    abstract fun showErrorState()
    abstract fun initViews()
    abstract fun initListeners()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        initViews()
        initListeners()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun setupToolbar() {}

    protected open fun handleDataState(dataState: DataState<*>) {
        when (dataState) {
            is DataState.Loading -> showLoadingState()
            is DataState.Success -> showContentState()
            is DataState.Error -> showErrorState()
        }
    }

    private fun observeViewModel() {
        viewModel.getStateLiveData().observe(viewLifecycleOwner) { dataState ->
            handleDataState(dataState)
        }
    }

}