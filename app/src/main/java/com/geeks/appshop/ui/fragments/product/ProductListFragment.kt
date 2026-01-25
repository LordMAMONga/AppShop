package com.geeks.appshop.ui.fragments.product

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.appshop.databinding.FragmentListBinding
import com.geeks.appshop.ui.adapters.ProductAdapter
import com.geeks.appshop.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductListFragment : BaseFragment<FragmentListBinding, ListViewModel>(
    FragmentListBinding::inflate
) {

    override val viewModel: ListViewModel by viewModel()
    private val adapter = ProductAdapter( { product ->
        val action = ProductListFragmentDirections.actionProductListFragmentToDetailFragment()
            .setProductId(product.id)
        findNavController().navigate(action)
    },
        onBuyClick = { product ->
            viewModel.addToCart(product)
            Toast.makeText(context, "Добавлено!", Toast.LENGTH_SHORT).show()
        })

    override fun onBind(binding: FragmentListBinding) {
        setupRecycler()

        viewModel.state.collectUiState(
            onLoading = {
                binding.progressBar.isVisible = true
                binding.rvProductList.isVisible = false
            },
            onSuccess = {
                binding.progressBar.isVisible = false
                binding.rvProductList.isVisible = true
                adapter.submitList(it)
            },
            onError = {
                binding.progressBar.isVisible = false
                binding.rvProductList.isVisible = false
            }
        )

        binding.btnCart.setOnClickListener {
            val action = ProductListFragmentDirections.actionProductListFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupRecycler() {
        binding.rvProductList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductList.adapter = adapter
    }
}