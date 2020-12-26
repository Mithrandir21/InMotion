package com.example.testapp.testing

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/** This rule will force all Rx Threads to perform in immediately. */
class RxJavaSchedulerImmediateRule : TestWatcher() {

    val immediate = TestScheduler()

    override fun starting(description: Description?) {
        super.starting(description)

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}