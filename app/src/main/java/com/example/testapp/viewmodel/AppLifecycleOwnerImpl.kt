package com.example.testapp.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import javax.inject.Inject

class AppLifecycleOwnerImpl @Inject constructor() : AppLifecycleOwner {

    /** Returns the Lifecycle owned by the Application Process. */
    override fun getLifeCycle(): Lifecycle = ProcessLifecycleOwner.get().lifecycle
}