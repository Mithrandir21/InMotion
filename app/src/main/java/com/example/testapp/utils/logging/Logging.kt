package com.example.testapp.utils.logging

import android.util.Log
import java.util.logging.Logger

/** Logging com.example.testapp.utils.logging.getTag. */
inline val Any.tag: String get() = javaClass.simpleName

/** Logs a com.example.testapp.utils.logging.verbose entry using default [Logger] implementation. */
inline fun Any.verbose(throwable: Throwable? = null, messageProvider: () -> String) {
    Log.v(tag, messageProvider(), throwable)
}

/** Logs a com.example.testapp.utils.logging.debug entry using default [Logger] implementation. */
inline fun Any.debug(throwable: Throwable? = null, messageProvider: () -> String) {
    Log.d(tag, messageProvider(), throwable)
}

/** Logs an com.example.testapp.utils.logging.info entry using default [Logger] implementation. */
inline fun Any.info(throwable: Throwable? = null, messageProvider: () -> String) {
    Log.i(tag, messageProvider(), throwable)
}

/** Logs a warning entry using default [Logger] implementation. */
inline fun Any.warn(throwable: Throwable? = null, messageProvider: () -> String) {
    Log.w(tag, messageProvider(), throwable)
}

/** Logs an com.example.testapp.utils.logging.error entry using default [Logger] implementation. */
inline fun Any.error(throwable: Throwable? = null, messageProvider: () -> String) {
    Log.e(tag, messageProvider(), throwable)
}

/** Logs a com.example.testapp.utils.logging.fatal com.example.testapp.utils.logging.error entry using default [Logger] implementation. */
inline fun Any.fatal(throwable: Throwable? = null, messageProvider: () -> String) {
    Log.wtf(tag, messageProvider(), throwable)
}
