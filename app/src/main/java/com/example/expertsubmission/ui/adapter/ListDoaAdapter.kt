package com.example.expertsubmission.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Doa
import com.example.expertsubmission.databinding.ItemDoaBinding

class ListDoaAdapter(private val onClickItem: (data: Doa) -> Unit) :
    RecyclerView.Adapter<ListDoaAdapter.ViewHolder>() {
    private var listDoa = emptyList<Doa>()

    class ViewHolder(var binding: ItemDoaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doa: Doa) {
            binding.tvDoaName.text = doa.doa
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listDoa.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listDoa[position]
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onClickItem(item)
        }
    }

    fun setData(data: List<Doa>) {
        val diffUtil = DoaCallback(listDoa, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listDoa = data
        diffResult.dispatchUpdatesTo(this)
    }
}