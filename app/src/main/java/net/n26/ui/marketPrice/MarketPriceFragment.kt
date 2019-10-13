package net.n26.ui.marketPrice

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_market_price.*
import javax.inject.Inject
import net.n26.R
import net.n26.data.model.Value
import net.n26.di.qualifiers.ViewModelInjection
import net.n26.di.ViewModelInjectionField
import net.n26.ui.base.BaseFragment
import net.n26.util.DateAxisValueFormatter

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

        setupChart()

        line_chart.visibility = View.GONE

        viewModel.get().getMarketPrice().observe(this, Observer {
            progressBar.visibility = View.GONE
            it.data?.let {
                buildLineChart(it.values)
            }
        })
    }

    @NonNull
    private fun createChartEntry(marketPriceEntity: Value): Entry {
        return Entry(marketPriceEntity.x.toFloat(), marketPriceEntity.y.toFloat())
    }

    private fun buildLineChart(entries: List<Value>?) {

        val values = Observable
            .fromIterable<Value>(entries)
            .map {
                this.createChartEntry(it)
            }
            .toList()
            .blockingGet()

        line_chart.data = LineData(marketPriceDataSet(values))
        line_chart.invalidate()

        line_chart.visibility = View.VISIBLE
    }

    private fun setupChart() {
        val description = Description()
        description.text = ""
        line_chart.description = description

        val xAxis = line_chart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = DateAxisValueFormatter(line_chart)
    }

    @NonNull
    private fun marketPriceDataSet(@NonNull values: List<Entry>): LineDataSet {
        val marketPriceDataSet = LineDataSet(values, "Market Price (USD)")
        marketPriceDataSet.setDrawIcons(false)
        marketPriceDataSet.color = Color.BLACK
        marketPriceDataSet.setCircleColor(Color.BLACK)
        marketPriceDataSet.circleHoleRadius = 0.5f
        marketPriceDataSet.circleRadius = 1f
        marketPriceDataSet.lineWidth = 1f
        marketPriceDataSet.setDrawFilled(false)
        marketPriceDataSet.formLineWidth = 1f
        marketPriceDataSet.formSize = 15f
        return marketPriceDataSet
    }
}