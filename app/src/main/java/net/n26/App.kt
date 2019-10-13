package net.n26

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.n26.di.components.DaggerAppComponent
import net.n26.di.modules.RepositoryModule
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .repository(RepositoryModule())
            .build()
            .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}