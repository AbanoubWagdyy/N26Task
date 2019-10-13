package net.n26.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.n26.ui.marketPrice.MarketPriceFragment
import net.n26.ui.marketPrice.MarketPriceModule

@Module
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector(modules = [MarketPriceModule::class])
    abstract fun marketPriceFragmentInjector(): MarketPriceFragment
}