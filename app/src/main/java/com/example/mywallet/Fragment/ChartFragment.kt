package com.example.mywallet.Fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mywallet.DataBase.DBHelper
import com.example.mywallet.databinding.FragmentChartBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class ChartFragment : Fragment() {

    lateinit var dbHelper: DBHelper
    lateinit var binding: FragmentChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentChartBinding.inflate(layoutInflater)
        dbHelper = DBHelper(context)

        var transList = dbHelper.getTransaction()
        var totalIncome = 0
        var totalExpense = 0

        for (trans in transList) {

            if (trans.isexpence == 0) {
                totalIncome += trans.amount
            } else if (trans.isexpence == 1) {
                totalExpense += trans.amount
            }

        }

        var overall = (totalIncome - totalExpense).toString()


        binding.chart.setUsePercentValues(true);
        binding.chart.getDescription().setEnabled(false);
        binding.chart.setExtraOffsets(5F, 10F, 5F, 5F);

        binding.chart.setDragDecelerationFrictionCoef(0.95f);

//        binding.chart.setCenterTextTypeface(tfLight);
        binding.chart.setCenterText(overall);

        binding.chart.setDrawHoleEnabled(true);
        binding.chart.setHoleColor(Color.WHITE);

        binding.chart.setTransparentCircleColor(Color.BLACK);
        binding.chart.setTransparentCircleAlpha(110);

        binding.chart.setHoleRadius(58f);
        binding.chart.setTransparentCircleRadius(61f);

        binding.chart.setDrawCenterText(true);

        binding.chart.setRotationAngle(0F);
        // enable rotation of the chart by touch
        binding.chart.setRotationEnabled(true);
        binding.chart.setHighlightPerTapEnabled(true);


        // add a selection listener
        val colors = ArrayList<Int>()


        colors.add(Color.RED)
        colors.add(Color.GREEN)

        val entries = ArrayList<PieEntry>()

        entries.add(PieEntry(totalExpense.toFloat(), totalExpense.toString()))
        entries.add(PieEntry(totalIncome.toFloat(), totalIncome.toString()))
        val dataSet = PieDataSet(entries, "Income")
        dataSet.setColors(colors)
        var pieData = PieData(dataSet)
        binding.chart.data = pieData

        return binding.root


    }

}


