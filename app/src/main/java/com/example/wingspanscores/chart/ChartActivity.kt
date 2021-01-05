package com.example.wingspanscores.chart

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wingspanscores.AppApplication
import com.example.wingspanscores.databinding.ActivityChartBinding
import com.example.wingspanscores.room.ScoreByPlayer
import com.example.wingspanscores.ui.main.IntegerValueFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class ChartActivity : AppCompatActivity() {

    private val viewModel: ChartViewModel by viewModels {
        ChartViewModelFactory((application as AppApplication).repository)
    }

    private lateinit var binding: ActivityChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")!!

        viewModel.getScoresByPlayer(name).observe(this, {
            // 折れ線DataSetsを生成
            val dataSets = createDataSets(it)

            // LineData設定
            val lineData = LineData(dataSets)
            lineData.setValueFormatter(IntegerValueFormatter())

            // Chart設定
            settingChart(binding.chart, lineData, it)
        })
    }

    /**
     * Chart設定
     */
    private fun settingChart(chart: LineChart, lineData: LineData, list: List<ScoreByPlayer>) {
        chart.apply {
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
                axisMaximum = (list.size - 1).toFloat()
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

    /**
     * 折れ線グラフDataSetsを生成する。
     */
    private fun createDataSets(scores: List<ScoreByPlayer>): MutableList<ILineDataSet> {
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

        return mutableListOf<ILineDataSet>().apply {
            add(birdsDataSet)
            add(bonusDataSet)
            add(roundDataSet)
            add(eggsDataSet)
            add(foodDataSet)
            add(tuckedDataSet)
            add(totalDataSet)
        }
    }
}
