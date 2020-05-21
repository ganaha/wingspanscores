package com.example.wingspanscores.ui.main

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter

class IntegerValueFormatter: ValueFormatter() {
    override fun getPointLabel(entry: Entry?): String {
        return entry?.y?.toInt().toString()
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return (value.toInt() + 1).toString()
    }
}