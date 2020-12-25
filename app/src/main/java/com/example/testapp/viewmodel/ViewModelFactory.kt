package com.example.testapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * A common factory for all `ViewModel`s.
 *
 * A provider function must exist, annotated with @[ViewModelMapKey] for the given `ViewModel` type
 * for the factory to be able to create an instance of it.
 * Otherwise, an [IllegalStateException] will be thrown upon `ViewModel` instance creation.
 */
@Singleton
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    /**
     * Creates a `ViewModel` of the given [type].
     *
     * A provider function must exist, annotated with @[ViewModelMapKey] for the given [type].
     * Otherwise, an [IllegalStateException] will be thrown upon `ViewModel` instance creation.
     */
    override fun <T : ViewModel?> create(type: Class<T>): T {
        val viewModel = viewModels[type]?.get()

        checkNotNull(viewModel) { "View Model NULL!" }

        check(type.isInstance(viewModel)) { "View Model not instance of $type!" }

        return type.cast(viewModel)
    }
}

