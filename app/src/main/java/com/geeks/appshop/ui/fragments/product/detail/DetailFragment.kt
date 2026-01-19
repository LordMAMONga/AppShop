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
import com.geeks.appshop.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.zip.Inflater

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.productId != -1){
            detailViewModel.loadProducts(args.productId)
        }

        observeState()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.state.collect { state ->
                    with(binding) {
                        when (state){
                            is UiState.Loading -> {}
                            is UiState.Success -> {
                                tvDetailTitle.text = state.data.title
                                tvDetailDescription.text = state.data.description
                                imgDetail.load(state.data.image){
                                    crossfade(true)
                                }
                            }
                            is UiState.Error -> {
                                Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}