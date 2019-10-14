package net.n26.data

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.n26.data.model.MarketPriceResponse
import net.n26.data.model.Resource
import net.n26.data.network.ServiceApi
import javax.inject.Inject

class DataRepository @Inject constructor(val mServiceApi: ServiceApi) : RepositorySource {

    val liveData = MutableLiveData<Resource<MarketPriceResponse>>()

    @SuppressLint("CheckResult")
    override fun getMarketPrice(): MutableLiveData<Resource<MarketPriceResponse>> {
        mServiceApi.getMarketPrice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                liveData.value = Resource.success(it)
            },{
                liveData.value = Resource.error(it,null)
            })
        return liveData
    }
}