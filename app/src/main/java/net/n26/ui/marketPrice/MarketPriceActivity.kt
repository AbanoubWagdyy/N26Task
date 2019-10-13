package net.n26.ui.marketPrice

import android.os.Bundle
import net.n26.R
import net.n26.ui.base.BaseActivity

class MarketPriceActivity : BaseActivity() {

    override fun layoutRes(): Int {
        return R.layout.activity_market_price
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(
            R.id.frame,
            MarketPriceFragment.newInstance()
        ).commitAllowingStateLoss()
    }
}
