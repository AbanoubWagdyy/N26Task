package net.n26.ui.marketPrice

import dagger.Module
import dagger.Provides
import net.n26.di.qualifiers.ViewModelInjection
import net.n26.di.InjectionViewModelProvider

@Module
class MarketPriceModule {

    @Provides
    @ViewModelInjection
    fun provideMarketPriceVM(
        fragment: MarketPriceFragment,
        viewModelProvider: InjectionViewModelProvider<MarketPriceViewModel>
    ) = viewModelProvider.get(fragment, MarketPriceViewModel::class)
}