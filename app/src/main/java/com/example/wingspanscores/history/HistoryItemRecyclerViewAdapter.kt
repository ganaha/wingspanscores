package com.example.wingspanscores.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wingspanscores.databinding.FragmentHistoryBinding
import com.example.wingspanscores.room.HistoryWithScores

class HistoryItemRecyclerViewAdapter(
    private val viewModel: HistoryViewModel,
    private val parentLifecycleOwner: LifecycleOwner
) : ListAdapter<HistoryWithScores, HistoryItemRecyclerViewAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentHistoryBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), parentLifecycleOwner, viewModel)
    }

    override fun getItemCount(): Int = viewModel.histories.value?.size ?: 0

    class ViewHolder(val binding: FragmentHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: HistoryWithScores,
            viewLifecycleOwner: LifecycleOwner,
            viewModel: HistoryViewModel
        ) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                history = item
                viewmodel = viewModel
                executePendingBindings()
            }
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<HistoryWithScores>() {
    override fun areItemsTheSame(oldItem: HistoryWithScores, newItem: HistoryWithScores): Boolean {
        return oldItem.history.id == newItem.history.id
    }

    override fun areContentsTheSame(
        oldItem: HistoryWithScores,
        newItem: HistoryWithScores
    ): Boolean {
        return oldItem == newItem
    }

}
