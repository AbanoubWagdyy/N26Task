package net.n26.data.network

import io.reactivex.Flowable
import net.n26.data.model.MarketPriceResponse
import retrofit2.http.*

interface ServiceApi {
    @GET("market-price")
    fun getMarketPrice(): Flowable<MarketPriceResponse>
}