package net.n26.data

import androidx.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Single
import net.n26.data.model.MarketPriceResponse
import net.n26.data.model.Resource

interface RepositorySource {
    fun getMarketPrice(): LiveData<Resource<MarketPriceResponse>>
}