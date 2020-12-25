package com.example.testapp.utils.logging

import io.reactivex.*

fun Completable.debugLog(identification: String = System.currentTimeMillis().toString()): Completable = this
    .doOnSubscribe { debug { "Completable onSubscribe ($identification)" } }
    .doOnComplete { debug { "Completable onComplete ($identification)" } }
    .doOnError { debug { "Completable onError ($identification) - Error: $it" } }
    .doOnDispose { debug { "Completable onDispose ($identification)" } }
    .doOnTerminate { debug { "Completable onTerminate ($identification)" } }
    .doAfterTerminate { debug { "Completable onAfterTerminate ($identification)" } }
    .doFinally { debug { "Completable onFinally ($identification)" } }

fun <T> Single<T>.debugLog(identification: String = System.currentTimeMillis().toString()): Single<T> = this
    .doOnSubscribe { debug { "Single onSubscribe ($identification)" } }
    .doOnSuccess { debug { "Single onSuccess ($identification) - Data: $it" } }
    .doOnError { debug { "Single onError ($identification) - Error: $it" } }
    .doOnDispose { debug { "Single onDispose ($identification)" } }
    .doAfterSuccess { debug { "Single onAfterSuccess ($identification)" } }
    .doAfterTerminate { debug { "Single onAfterTerminate ($identification)" } }
    .doFinally { debug { "Single onFinally ($identification)" } }

fun <T> Maybe<T>.debugLog(identification: String = System.currentTimeMillis().toString()): Maybe<T> = this
    .doOnSubscribe { debug { "Maybe onSubscribe ($identification)" } }
    .doOnSuccess { debug { "Maybe onSuccess ($identification) - Data: $it" } }
    .doOnComplete { debug { "Maybe onComplete ($identification)" } }
    .doOnError { debug { "Maybe onError ($identification) - Error: $it" } }
    .doOnDispose { debug { "Maybe onDispose ($identification)" } }
    .doAfterSuccess { debug { "Maybe onAfterSuccess ($identification)" } }
    .doAfterTerminate { debug { "Maybe onAfterTerminate ($identification)" } }
    .doFinally { debug { "Maybe onFinally ($identification)" } }

fun <T> Observable<T>.debugLog(identification: String = System.currentTimeMillis().toString()): Observable<T> = this
    .doOnSubscribe { debug { "Observable onSubscribe ($identification)" } }
    .doOnNext { debug { "Observable onNext ($identification) - Data: $it" } }
    .doOnComplete { debug { "Observable onComplete ($identification)" } }
    .doOnError { debug { "Observable onError ($identification) - Error: $it" } }
    .doOnDispose { debug { "Observable onDispose ($identification)" } }
    .doOnTerminate { debug { "Observable onTerminate ($identification)" } }
    .doAfterNext { debug { "Observable onAfterNext ($identification) - Data: $it" } }
    .doAfterTerminate { debug { "Observable onAfterTerminate ($identification)" } }
    .doFinally { debug { "Observable onFinally ($identification)" } }

fun <T> Flowable<T>.debugLog(identification: String = System.currentTimeMillis().toString()): Flowable<T> = this
    .doOnSubscribe { debug { "Flowable onSubscribe ($identification)" } }
    .doOnNext { debug { "Flowable onNext ($identification) - Data: $it" } }
    .doOnComplete { debug { "Flowable onComplete ($identification)" } }
    .doOnError { debug { "Flowable onError ($identification) - Error: $it" } }
    .doOnCancel { debug { "Flowable onCancel ($identification)" } }
    .doOnTerminate { debug { "Flowable onTerminate ($identification)" } }
    .doOnRequest { debug { "Flowable onRequest ($identification)" } }
    .doAfterNext { debug { "Flowable onAfterNext ($identification) - Data: $it" } }
    .doAfterTerminate { debug { "Flowable onAfterTerminate ($identification)" } }
    .doFinally { debug { "Flowable onFinally ($identification)" } }
