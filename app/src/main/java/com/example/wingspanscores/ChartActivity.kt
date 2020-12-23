package com.example.wingspanscores

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wingspanscores.ui.main.IntegerValueFormatter
import com.example.wingspanscores.ui.main.room.AppDao
import com.example.wingspanscores.ui.main.room.AppDatabase
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val name = intent.getStringExtra("name")
        val dao = AppDatabase.getDatabase(this).appDao()

        CoroutineScope(Job() + Dispatchers.Main).launch {
            val scores = name?.let { dao.getScoresByPlayer(it) }

            // 折れ線DataSetsを生成
            val dataSets = scores?.let { createDataSets(it) }

            val lineData = LineData(dataSets)
            lineData.setValueFormatter(IntegerValueFormatter())

            findViewById<LineChart>(R.id.chart).apply {
                axisLeft.apply {
                    axisMinimum = 0f
                    axisMaximum = 120f
                    labelCount = 13
                    enableGridDashedLine(10f, 10f, 0f)
                    setDrawZeroLine(true)
                }
                axisRight.apply {
                    axisMinimum = 0f
                    axisMaximum = 120f
                    labelCount = 13
                    setDrawZeroLine(true)
                }
                xAxis.apply {
                    axisMinimum = 0f
                    if (scores != null) {
                        axisMaximum = (scores.size - 1).toFloat()
                    }
                    labelCount = 10
                    position = XAxis.XAxisPosition.BOTTOM
                    enableGridDashedLine(10f, 10f, 0f)
                    valueFormatter = IntegerValueFormatter()
                }
                description.isEnabled = false
                isHighlightPerTapEnabled = false
                isHighlightPerDragEnabled = false
                data = lineData
                setVisibleXRangeMaximum(10F)
                invalidate()
            }
        }
    }

    /**
     * 折れ線グラフDataSetsを生成する。
     */
    private fun createDataSets(scores: List<AppDao.ScoreByPlayer>): MutableList<ILineDataSet> {
        val birdsValues = ArrayList<Entry>()
        val bonusValues = ArrayList<Entry>()
        val roundValues = ArrayList<Entry>()
        val eggsValues = ArrayList<Entry>()
        val foodValues = ArrayList<Entry>()
        val tuckedValues = ArrayList<Entry>()
        val totalValues = ArrayList<Entry>()

        for (i in scores.indices) {
            birdsValues.add(Entry(i.toFloat(), scores[i].birds.toFloat()))
            bonusValues.add(Entry(i.toFloat(), scores[i].bonus.toFloat()))
            roundValues.add(Entry(i.toFloat(), scores[i].round.toFloat()))
            eggsValues.add(Entry(i.toFloat(), scores[i].eggs.toFloat()))
            foodValues.add(Entry(i.toFloat(), scores[i].food.toFloat()))
            tuckedValues.add(Entry(i.toFloat(), scores[i].tucked.toFloat()))
            totalValues.add(Entry(i.toFloat(), scores[i].total.toFloat()))
        }

        val birdsDataSet = LineDataSet(birdsValues, "BIRDS").apply {
            color = Color.CYAN
        }
        val bonusDataSet = LineDataSet(bonusValues, "BONUS").apply {
            color = Color.MAGENTA
        }
        val roundDataSet = LineDataSet(roundValues, "ROUND").apply {
            color = Color.BLUE
        }
        val eggsDataSet = LineDataSet(eggsValues, "EGGS").apply {
            color = Color.BLACK
        }
        val foodDataSet = LineDataSet(foodValues, "FOOD").apply {
            color = Color.GREEN
        }
        val tuckedDataSet = LineDataSet(tuckedValues, "TUCKED").apply {
            color = Color.LTGRAY
        }
        val totalDataSet = LineDataSet(totalValues, "TOTAL").apply {
            color = Color.RED
        }

        val dataSets = mutableListOf<ILineDataSet>()
        dataSets.add(birdsDataSet)
        dataSets.add(bonusDataSet)
        dataSets.add(roundDataSet)
        dataSets.add(eggsDataSet)
        dataSets.add(foodDataSet)
        dataSets.add(tuckedDataSet)
        dataSets.add(totalDataSet)

        return dataSets
    }
}
