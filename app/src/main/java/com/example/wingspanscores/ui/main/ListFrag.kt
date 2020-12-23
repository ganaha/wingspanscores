package com.example.wingspanscores.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wingspanscores.ChartActivity
import com.example.wingspanscores.R
import com.example.wingspanscores.ui.main.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListFrag : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var adapter: SimpleAdapter
    private lateinit var items: List<Map<String, String>>
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.listView)
        listView.onItemClickListener = this

        refresh()
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val intent = Intent(context, ChartActivity::class.java).apply {
            val title = p1?.findViewById<TextView>(android.R.id.text1)?.text
            putExtra("name", title)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            val dao = AppDatabase.getDatabase(requireContext()).appDao()
            val scores = dao.getPlayersForList()
            items = scores.map {
                mapOf(
                    "title" to it.name,
                    "detail" to "plays: ${it.game}, win: ${it.win}, best score: ${it.best}"
                )
            }

            adapter = SimpleAdapter(
                context,
                items,
                android.R.layout.simple_list_item_2,
                arrayOf("title", "detail"),
                intArrayOf(android.R.id.text1, android.R.id.text2)
            )
            listView.adapter = adapter
        }
    }
}