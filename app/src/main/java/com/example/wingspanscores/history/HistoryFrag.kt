package com.example.wingspanscores.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wingspanscores.AppApplication
import com.example.wingspanscores.databinding.FragmentHistoryListBinding

/**
 * A fragment representing a list of Items.
 */
class HistoryFrag : Fragment() {

    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((requireContext().applicationContext as AppApplication).repository)
    }

    private lateinit var binding: FragmentHistoryListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = HistoryItemRecyclerViewAdapter(viewModel, viewLifecycleOwner)

                // 罫線
                val divider = DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
                addItemDecoration(divider)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresh()
    }

    private fun refresh() {
        viewModel.histories.observe(viewLifecycleOwner, {
            Log.d("TAG", "viewModel.histories.observe: ${it.size}")
            binding.executePendingBindings()
        })
    }
}