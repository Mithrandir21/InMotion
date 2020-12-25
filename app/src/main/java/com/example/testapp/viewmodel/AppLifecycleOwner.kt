package com.example.testapp.viewmodel

import androidx.lifecycle.Lifecycle

interface AppLifecycleOwner {

    /** Returns the Lifecycle owned by the Application Process. */
    fun getLifeCycle(): Lifecycle
}