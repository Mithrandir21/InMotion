package com.example.testapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.utils.livedata.LiveEvent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    private val inProgress = LiveEvent<Boolean>()

    protected val compositeDisposable = CompositeDisposable()
    protected val error = LiveEvent<Throwable>()

    fun observeProgress(): LiveData<Boolean> = inProgress
    fun observeErrors(): LiveData<Throwable> = error

    fun startProgress() = inProgress.postValue(true)
    fun stopProgress() = inProgress.postValue(false)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}