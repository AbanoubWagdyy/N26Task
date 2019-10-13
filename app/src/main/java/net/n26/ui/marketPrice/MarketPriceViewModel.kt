package net.n26.ui.marketPrice

import androidx.lifecycle.LiveData
import net.n26.data.RepositorySource
import net.n26.data.model.MarketPriceResponse
import net.n26.data.model.Resource
import javax.inject.Inject
import net.n26.ui.base.BaseViewModel

class MarketPriceViewModel @Inject constructor(val mRepositorySource: RepositorySource) : BaseViewModel() {

    fun getMarketPrice(): LiveData<Resource<MarketPriceResponse>> {
        return mRepositorySource.getMarketPrice()
    }
}