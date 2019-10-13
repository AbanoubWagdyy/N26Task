package net.n26.ui.marketPrice

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.fragment_market_price.*
import javax.inject.Inject
import net.n26.R
import net.n26.data.model.Value
import net.n26.di.qualifiers.ViewModelInjection
import net.n26.di.ViewModelInjectionField
import net.n26.ui.base.BaseFragment
import java.util.ArrayList

class MarketPriceFragment : BaseFragment() {

    override fun layoutRes() = R.layout.fragment_market_price

    companion object {
        fun newInstance(): MarketPriceFragment {
            return MarketPriceFragment()
        }
    }

    @Inject
    @ViewModelInjection
    lateinit var viewModel: ViewModelInjectionField<MarketPriceViewModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        line_chart.visibility = View.GONE

        viewModel.get().getMarketPrice().observe(this, Observer {
            progressBar.visibility = View.GONE
            it.data?.let {
                buildLineChart(it.values)
            }
        })
    }

    private fun buildLineChart(entries: List<Value>?) {
        val values = mutableListOf<Entry>()

        if (entries != null) {
            if (entries.isNotEmpty() && entries.size > 20) {
                for (i in 0..20) {
                    values.add(Entry(entries[i].x.toFloat(), entries[i].y.toFloat()))
                }
            } else {
                for (i in entries.indices) {
                    values.add(Entry(entries[i].x.toFloat(), entries[i].y.toFloat()))
                }
            }
        }

        if (entries?.isNotEmpty()!!) {
            val set1: LineDataSet

            if (line_chart.getData() != null && line_chart.getData().getDataSetCount() > 0) run {
                set1 = line_chart.getData().getDataSetByIndex(0) as LineDataSet
                set1.setValues(values)
                set1.notifyDataSetChanged()
                line_chart.getData().notifyDataChanged()
                line_chart.notifyDataSetChanged()
            } else {
                // create a dataset and give it a type
                set1 = LineDataSet(values, "Market Price")

                set1.setDrawIcons(false)

                // draw dashed line
                set1.enableDashedLine(10f, 5f, 0f)

                // black lines and points
                set1.setColor(Color.BLACK)
                set1.setCircleColor(Color.BLACK)

                // line thickness and point size
                set1.setLineWidth(1f)
                set1.setCircleRadius(3f)

                // draw points as solid circles
                set1.setDrawCircleHole(false)

                // customize legend entry
                set1.setFormLineWidth(1f)
                set1.setFormLineDashEffect(DashPathEffect(floatArrayOf(10f, 5f), 0f))
                set1.setFormSize(15f)

                // text size of values
                set1.setValueTextSize(9f)

                // draw selection line as dashed
                set1.enableDashedHighlightLine(10f, 5f, 0f)

                // set the filled area
                set1.setDrawFilled(true)
                set1.fillFormatter = IFillFormatter { dataSet, dataProvider ->
                    line_chart.getAxisLeft().getAxisMinimum()
                }

                // set color of filled area
                if (Utils.getSDKInt() >= 18) {
                    // drawables only supported on api level 18 and above
                    val drawable =
                        context?.let { ContextCompat.getDrawable(it, R.drawable.fade_red) }
                    set1.setFillDrawable(drawable)
                } else {
                    set1.setFillColor(Color.BLACK)
                }

                val dataSets = ArrayList<ILineDataSet>()
                dataSets.add(set1)

                val data = LineData(dataSets)

                line_chart.setData(data)
            }
            line_chart.visibility = View.VISIBLE
        }
    }
}