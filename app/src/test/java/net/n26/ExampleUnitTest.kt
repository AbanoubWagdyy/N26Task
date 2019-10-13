package net.n26

import net.n26.data.RepositorySource
import net.n26.di.components.DaggerAppComponent
import net.n26.di.modules.RepositoryModule
import net.n26.ui.marketPrice.MarketPriceViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Mock
    private val repository: RepositorySource? = null


    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        DaggerAppComponent.builder().application(App()).repository(RepositoryModule()).build()
    }

    @Test
    fun validateRepo() {
        Mockito.verify(repository, Mockito.never())
    }

    @Test
    fun getMarketPrice() {
        repository?.getMarketPrice()?.observeForever {

        }
    }
}
