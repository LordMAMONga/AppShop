package com.geeks.appshop.ui.fragments.product.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil3.load
import coil3.request.crossfade
import com.geeks.appshop.R
import com.geeks.appshop.databinding.FragmentDetailBinding
import com.geeks.appshop.ui.base.BaseFragment
import com.geeks.appshop.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.zip.Inflater

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {

    val args: DetailFragmentArgs by navArgs()

    override val viewModel: DetailViewModel by viewModel()

    override fun onBind(binding: FragmentDetailBinding) {
        if (args.productId != -1) {
            viewModel.loadProducts(args.productId)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.state.collectUiState(
            onSuccess = { product ->
                with(binding) {

                    tvDetailTitle.text = product.title
                    tvDetailDescription.text = product.description
                    imgDetail.load(product.image) {
                        crossfade(true)
                    }
                }

            }
        )
    }

}