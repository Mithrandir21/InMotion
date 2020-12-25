package com.example.testapp.di

import com.example.testapp.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/**
 * An interface defining the way dependency injection is handled in the application.
 *
 * Usage example (in [App.onCreate]):
 * ```
 * DaggerAppInjector.builder()
 *     .application(this)  // Define the instance of the app for further dependency injections
 *     .build()            // Build an AppInjector
 *     .inject(this)       // Inject dependencies into the app
 * ```
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppDependencyProvider::class])
interface AppInjector {

    /** Injects dependencies into the [app]. */
    fun inject(app: App)

    /** An interface defining [AppInjector] building process. */
    @Component.Builder
    interface Builder {

        /** Defines the [app] instance to be used for dependency injection. */
        @BindsInstance
        fun application(app: App): Builder

        /** Builds an [AppInjector]. */
        fun build(): AppInjector
    }
}
