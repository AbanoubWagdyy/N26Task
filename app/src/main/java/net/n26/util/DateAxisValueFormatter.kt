package net.n26.util

import android.text.format.DateFormat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

internal class DateAxisValueFormatter(private val chart: LineChart) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        val format = if (chart.visibleXRange / DAY > 30 * 6) "MMM yy" else "dd MMM"
        return DateFormat.format(format, value.toLong()) as String
    }

    companion object {
        private val DAY = (24 * 60 * 60 * 1000).toLong()
    }
}