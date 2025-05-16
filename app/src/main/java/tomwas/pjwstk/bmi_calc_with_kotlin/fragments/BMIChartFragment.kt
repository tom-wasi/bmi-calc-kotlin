package tomwas.pjwstk.bmi_calc_with_kotlin.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import tomwas.pjwstk.bmi_calc_with_kotlin.R

class BMIChartFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_bmi_chart, container, false)
        val chart = view.findViewById<LineChart>(R.id.bmiChart)

        val entries = listOf(
            Entry(1f, 24.0f),
            Entry(2f, 23.7f),
            Entry(3f, 23.0f),
            Entry(4f, 22.4f),
            Entry(5f, 22.0f)
        )

        val dataSet = LineDataSet(entries, "BMI w czasie").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
        }

        chart.data = LineData(dataSet)
        chart.description.text = "Body-Mass index"
        chart.invalidate()

        return view
    }
}