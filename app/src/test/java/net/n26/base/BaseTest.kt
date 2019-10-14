package io.github.philippeboisney.retrokotlin.base

import net.n26.App
import net.n26.di.components.DaggerAppComponent
import net.n26.di.modules.RepositoryModule
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        DaggerAppComponent.builder().application(App()).repository(RepositoryModule()).build()
            .inject(
                App()
            )
    }
}