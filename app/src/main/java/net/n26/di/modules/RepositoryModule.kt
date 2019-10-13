package net.n26.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import net.n26.data.DataRepository
import net.n26.data.RepositorySource
import net.n26.data.network.ServiceApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class
    ]
)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(serviceApi: ServiceApi): RepositorySource {
        return DataRepository(serviceApi)
    }
}