package com.example.expertsubmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.Resource
import com.example.expertsubmission.databinding.FragmentHomeBinding
import com.example.expertsubmission.ui.adapter.ListDoaAdapter
import com.example.expertsubmission.ui.viewmodel.HomeViewModel
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()
    private val listDoaAdapter: ListDoaAdapter by lazy {
        ListDoaAdapter { doa ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(doa)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDoa.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDoa.adapter = listDoaAdapter

        binding.btnFavorite.setOnClickListener {
            moveToFavorite()
        }

        homeViewModel.listDoa.observe(viewLifecycleOwner) { doa ->
            if (doa != null) {
                when (doa) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        doa.data?.let { listDoaAdapter.setData(it) }
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), doa.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun moveToFavorite() {
        val splitInstallManager = SplitInstallManagerFactory.create(requireContext())
        val moduleFav = "favorite"
        if (splitInstallManager.installedModules.contains(moduleFav)) {
            moveToFavoriteNav()
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleFav)
                .build()

            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Success installing module", Toast.LENGTH_SHORT).show()
                    moveToFavoriteNav()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Error installing module $it", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun moveToFavoriteNav() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFavoriteFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}