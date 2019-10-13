package net.n26.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.n26.ui.marketPrice.MarketPriceActivity

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMarketPriceActivity(): MarketPriceActivity
}