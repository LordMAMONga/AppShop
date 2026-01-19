package com.geeks.appshop.ui.fragments.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.appshop.databinding.FragmentListBinding
import com.geeks.appshop.ui.adapters.ProductAdapter
import com.geeks.appshop.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val listViewModel: ListViewModel by viewModel()
    private val adapter = ProductAdapter{ product ->
        val action = ProductListFragmentDirections.actionProductListFragmentToDetailFragment().setProductId(product.id)

        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeState()
    }

    private fun setupRecycler() {
        binding.rvProductList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductList.adapter = adapter
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                listViewModel.state.collect { state ->
                    with(binding) {
                        when (state) {
                            is UiState.Loading -> {
                                progressBar.isVisible = true
                                rvProductList.isVisible = false
                            }

                            is UiState.Success -> {
                                progressBar.isVisible = false
                                rvProductList.isVisible = true
                                adapter.submitList(state.data)
                            }

                            is UiState.Error -> {
                                progressBar.isVisible = false
                                rvProductList.isVisible = false
                                Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}