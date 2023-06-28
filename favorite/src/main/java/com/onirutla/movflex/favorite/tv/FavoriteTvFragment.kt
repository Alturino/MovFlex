package com.onirutla.movflex.favorite.tv

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.onirutla.movflex.favorite.databinding.FragmentFavoriteTvBinding
import com.onirutla.movflex.tv.ui.adapter.TvPagingVerticalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteTvFragment : Fragment() {

    private var _binding: FragmentFavoriteTvBinding? = null
    private val binding get() = _binding!!

    private val vm: FavoriteTvViewModel by viewModels()

    private val itemContentVerticalAdapter by lazy {
        TvPagingVerticalAdapter { view, content ->
            view.findNavController().navigate(Uri.parse("movflex://main_nav/tv/${content.id}"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        vm.tvFavorite.observe(viewLifecycleOwner) {
//            itemContentVerticalAdapter.submitData(viewLifecycleOwner.lifecycle, it)
//        }

        binding.favoriteTvPaging.apply {
            adapter = itemContentVerticalAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
