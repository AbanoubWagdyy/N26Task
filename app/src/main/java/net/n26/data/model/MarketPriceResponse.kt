package net.n26.data.model

import com.google.gson.annotations.SerializedName

class MarketPriceResponse(throwable: Throwable) {

    @SerializedName("description")
    var description: String = ""
    @SerializedName("name")
    var name: String = ""
    @SerializedName("period")
    var period: String = ""
    @SerializedName("status")
    var status: String = ""
    @SerializedName("unit")
    var unit: String = ""
    @SerializedName("values")
    var values: List<Value>? = null

    var throwable: Throwable? = throwable
}