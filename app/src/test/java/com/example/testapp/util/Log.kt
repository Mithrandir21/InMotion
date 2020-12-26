@file:Suppress("PackageDirectoryMismatch")

package android.util

/** This Logger class is created to not have to Mock the Logger function in Android for Unit Tests. */
object Log {

    @JvmStatic
    fun d(tag: String, msg: String, throwable: Throwable?): Int {
        println("DEBUG: Tag[$tag] - Msg[$msg] - Throwable[$throwable]")
        return 0
    }

    @JvmStatic
    fun i(tag: String, msg: String, throwable: Throwable?): Int {
        println("INFO: Tag[$tag] - Msg[$msg] - Throwable[$throwable]")
        return 0
    }

    @JvmStatic
    fun w(tag: String, msg: String, throwable: Throwable?): Int {
        println("WARN: Tag[$tag] - Msg[$msg] - Throwable[$throwable]")
        return 0
    }

    @JvmStatic
    fun e(tag: String, msg: String, throwable: Throwable?): Int {
        println("ERROR: Tag[$tag] - Msg[$msg] - Throwable[$throwable]")
        return 0
    }
}
