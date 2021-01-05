package com.example.wingspanscores.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wingspanscores.AppApplication
import com.example.wingspanscores.chart.ChartActivity
import com.example.wingspanscores.databinding.FragmentListBinding

class ListFrag : Fragment(), AdapterView.OnItemClickListener {

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory((requireContext().applicationContext as AppApplication).repository)
    }

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listView.onItemClickListener = this

        refresh()
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val intent = Intent(context, ChartActivity::class.java).apply {
            val title = p1?.findViewById<TextView>(android.R.id.text1)?.text
            putExtra("name", title)
        }
        startActivity(intent)
    }

    private fun refresh() {
        viewModel.players.observe(viewLifecycleOwner, {
            val items = it.map { player ->
                mapOf(
                    "title" to player.name,
                    "detail" to "play: ${player.game}, win: ${player.win}, best: ${player.best}"
                )
            }
            val adapter = SimpleAdapter(
                context,
                items,
                android.R.layout.simple_list_item_2,
                arrayOf("title", "detail"),
                intArrayOf(android.R.id.text1, android.R.id.text2)
            )
            binding.listView.adapter = adapter
        })
    }
}