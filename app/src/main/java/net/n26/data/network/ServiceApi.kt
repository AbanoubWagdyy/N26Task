package net.n26.data.network

import androidx.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import net.n26.data.model.MarketPriceResponse
import retrofit2.http.*

interface ServiceApi {
    @GET("market-price")
    fun getMarketPrice(): Single<MarketPriceResponse>
}