package net.n26.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import io.reactivex.schedulers.Schedulers
import net.n26.data.model.MarketPriceResponse
import net.n26.data.model.Resource
import net.n26.data.network.ServiceApi
import javax.inject.Inject

class DataRepository @Inject constructor(val mServiceApi: ServiceApi) : RepositorySource {

    override fun getMarketPrice(): LiveData<Resource<MarketPriceResponse>> {

        return LiveDataReactiveStreams.fromPublisher<Resource<MarketPriceResponse>>(
            mServiceApi.getMarketPrice()
                .onErrorReturn { throwable ->
                    val marketPriceResponse = MarketPriceResponse(throwable)
                    marketPriceResponse
                }
                .map { marketPrice ->
                    Resource.success(
                        marketPrice
                    )
                }
                .subscribeOn(Schedulers.io())
        )
    }
}