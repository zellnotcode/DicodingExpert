package com.example.expertsubmission.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import androidx.navigation.fragment.findNavController
import com.example.expertsubmission.R

class ProgressFragment : AbstractProgressFragment(R.layout.fragment_progress) {
    private lateinit var textView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.tv_progress)
        textView.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onCancelled() {
        Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    override fun onFailed(errorCode: Int) {
        Toast.makeText(requireContext(), "Error $errorCode", Toast.LENGTH_SHORT).show()
        textView.text = resources.getString(R.string.error)
    }

    override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        Toast.makeText(requireContext(), "On Progress", Toast.LENGTH_SHORT).show()
        textView.text =
            resources.getString(R.string.Loading, (bytesDownloaded / bytesTotal * 100).toString())
    }
}