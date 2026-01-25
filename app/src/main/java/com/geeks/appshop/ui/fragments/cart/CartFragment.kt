package com.geeks.appshop.ui.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.appshop.R
import com.geeks.appshop.databinding.FragmentCartBinding
import com.geeks.appshop.ui.adapters.CartAdapter
import com.geeks.appshop.ui.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>(
    FragmentCartBinding::inflate
) {
    override val viewModel: CartViewModel by viewModel()

    private val adapter = CartAdapter()

    override fun onBind(binding: FragmentCartBinding) {
        setupRecycler()

        viewModel.items.collectFlow { state ->
            adapter.submitList(state)
            binding.btnCheckout.isEnabled = state.isNotEmpty()
        }

        viewModel.total.collectFlow { state ->
            binding.tvTotalPrice.text = "$ %.2f".format(state)

        }


        binding.btnCheckout.setOnClickListener {
            viewModel.checkout()
        }

    }

    private fun setupRecycler() {
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter
    }

}