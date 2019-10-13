package net.n26.di.modules

import dagger.Module
import dagger.Provides
import net.n26.data.network.ServiceApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
class NetworkModule {

    private val _url = "https://api.blockchain.info/charts/"

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(ResponseCacheInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(_url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    class ResponseCacheInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalResponse = chain.proceed(chain.request())
            return if (chain.request().method == "GET") {
                originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=0")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build()
            } else {
                originalResponse
            }
        }
    }
}