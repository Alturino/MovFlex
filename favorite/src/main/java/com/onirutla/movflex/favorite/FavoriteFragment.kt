package com.onirutla.movflex.favorite

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.onirutla.movflex.core.domain.model.type.ItemType
import com.onirutla.movflex.core.ui.ItemContentPagingVerticalAdapter
import com.onirutla.movflex.di.FavoriteModuleDependencies
import com.onirutla.movflex.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels { factory }

    private var favoriteAdapter: ItemContentPagingVerticalAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            ).build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = ItemContentPagingVerticalAdapter { itemView, content ->
            when (content.itemType) {
                ItemType.Movie -> {
                    itemView.findNavController()
                        .navigate(Uri.parse("movflex://main_nav/movie/${content.id}"))
                }
                ItemType.Tv -> {
                    itemView.findNavController()
                        .navigate(Uri.parse("movflex://main_nav/tv/${content.id}"))
                }
            }
        }

        viewModel.favorite.observe(viewLifecycleOwner) {
            favoriteAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }

        binding.favoritePaging.apply {
            adapter = favoriteAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        favoriteAdapter = null
    }

}
