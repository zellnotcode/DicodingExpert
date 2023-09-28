package com.example.expertsubmission.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expertsubmission.R
import com.example.expertsubmission.databinding.FragmentDetailBinding
import com.example.expertsubmission.ui.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }

        setDataDetail()
    }

    private fun setDataDetail() {
        with(binding) {
            tvDoaNameDetail.text = args.doa.doa
            tvDoaArab.text = args.doa.ayat
            tvDoaLatin.text = args.doa.latin
            tvArtiDoa.text = args.doa.artinya
        }
        var statusFav = args.doa.favorite
        setStatusFavorite(statusFav)
        binding.btnFavorite.setOnClickListener {
            detailViewModel.setFavoriteDoa(args.doa, !args.doa.favorite)
            statusFav = !statusFav
            val message = if (!statusFav) {
                getString(R.string.removed)
            } else {
                getString(R.string.added)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            setStatusFavorite(statusFav)
        }
    }

    private fun setStatusFavorite(status: Boolean) {
        if (status) {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite_filled
                )
            )
        } else {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite_outlined
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}