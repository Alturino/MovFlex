package com.onirutla.movflex.ui.tv.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.databinding.FragmentTvMoreBinding
import com.onirutla.movflex.ui.adapter.ItemPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvMoreFragment : Fragment() {

    private var _binding: FragmentTvMoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvMoreViewModel by viewModels()

    private val args: TvMoreFragmentArgs by navArgs()

    private val itemPagingAdapter by lazy {
        ItemPagingAdapter { view, itemId ->
            view.findNavController().navigate(
                TvMoreFragmentDirections.actionTvMoreFragmentToTvDetailFragment(itemId)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTvByCategory(args.tvType)
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
            adapter = itemPagingAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.toolbar.apply {
            title = args.tvType.value
            setNavigationOnClickListener { it.findNavController().navigateUp() }
        }

        viewModel.tvMore.observe(viewLifecycleOwner) {
            itemPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}