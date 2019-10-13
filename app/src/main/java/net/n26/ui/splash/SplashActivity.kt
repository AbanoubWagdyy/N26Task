package net.n26.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import net.n26.R
import net.n26.ui.marketPrice.MarketPriceActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, MarketPriceActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
