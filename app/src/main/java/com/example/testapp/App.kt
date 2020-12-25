package com.example.testapp

import androidx.multidex.MultiDexApplication
import com.example.testapp.di.DaggerAppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : MultiDexApplication(), HasAndroidInjector {

    /** Returns an [AndroidInjector].  */
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    /**
     * Called when the application is starting.
     *
     * Initializes Fabric and dependency injection.
     */
    override fun onCreate() {
        super.onCreate()

        DaggerAppInjector.builder()
                .application(this)
                .build()
                .inject(this)
    }
}
