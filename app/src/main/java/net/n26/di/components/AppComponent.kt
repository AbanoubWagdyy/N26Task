package net.n26.di.components

import net.n26.App
import net.n26.di.modules.ActivityInjectorsModule
import net.n26.di.modules.FragmentInjectorsModule
import net.n26.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import net.n26.di.modules.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityInjectorsModule::class,
        FragmentInjectorsModule::class,
        RepositoryModule::class,
        AppModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun repository(repositoryModule: RepositoryModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}