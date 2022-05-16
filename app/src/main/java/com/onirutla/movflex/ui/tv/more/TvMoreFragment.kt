package com.onirutla.movflex.ui.tv.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.databinding.FragmentTvMoreBinding
import com.onirutla.movflex.ui.adapter.ItemPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TvMoreFragment : Fragment() {

    private var _binding: FragmentTvMoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvMoreViewModel by viewModels()

    private val args: TvMoreFragmentArgs by navArgs()

    private val movieMoreAdapter by lazy {
        ItemPagingAdapter { view, itemId ->
            view.findNavController()
                .navigate(
                    TvMoreFragmentDirections.actionTvMoreFragmentToTvDetailFragment(itemId)
                )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category = args.category
        viewModel.getTvByCategory(category)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviePaging.apply {
            adapter = movieMoreAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.toolbar.apply {
            title = args.category
            setNavigationOnClickListener { it.findNavController().navigateUp() }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tv.collect {
                    movieMoreAdapter.submitData(it)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}