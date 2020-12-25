package com.example.testapp.utils

class Optional<T> private constructor(var value: T?) {

    companion object {
        fun <T> of(value: T?): Optional<T> {
            return Optional(value)
        }
    }
}