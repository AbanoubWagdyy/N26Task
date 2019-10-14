package net.n26.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Flowable
import io.reactivex.Single
import net.n26.data.model.MarketPriceResponse
import net.n26.data.model.Resource

interface RepositorySource {
    fun getMarketPrice(): MutableLiveData<Resource<MarketPriceResponse>>
}