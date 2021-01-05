package com.example.wingspanscores.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.wingspanscores.databinding.FragmentHistoryBinding

class HistoryItemRecyclerViewAdapter(
    private val viewModel: HistoryViewModel,
    private val parentLifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<HistoryItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentHistoryBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            viewmodel = viewModel
            history = viewModel.histories.value?.getOrNull(position)
            lifecycleOwner = parentLifecycleOwner
        }
    }

    override fun getItemCount(): Int = viewModel.histories.value?.size ?: 0

    inner class ViewHolder(val binding: FragmentHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}