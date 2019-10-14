package net.n26.ui.marketPrice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import net.n26.data.RepositorySource
import net.n26.data.model.MarketPriceResponse
import net.n26.data.model.Resource
import javax.inject.Inject
import net.n26.ui.base.BaseViewModel

class MarketPriceViewModel @Inject constructor(val mRepositorySource: RepositorySource) :
    BaseViewModel() {


    var liveData = MutableLiveData<Resource<MarketPriceResponse>>()

    fun getMarketPrice(): MutableLiveData<Resource<MarketPriceResponse>> {
        liveData = mRepositorySource.getMarketPrice()
        return liveData
    }
}