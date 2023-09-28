package com.example.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.adapter.FavoriteAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.FavoriteModule
import com.example.favorite.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter { doa ->
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(doa)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(FavoriteModule)

        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorite.adapter = favoriteAdapter

        binding.moveHome.setOnClickListener {
            findNavController().navigateUp()
        }

        favoriteViewModel.favoriteData.observe(viewLifecycleOwner) { doa ->
            if (doa.isNullOrEmpty()) {
                binding.tvNoData.visibility = View.VISIBLE
            } else {
                binding.tvNoData.visibility = View.GONE
                favoriteAdapter.setData(doa)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        unloadKoinModules(FavoriteModule)
    }
}