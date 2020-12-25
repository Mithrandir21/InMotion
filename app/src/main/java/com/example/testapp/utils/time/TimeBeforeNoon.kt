package com.example.testapp.utils.time

interface TimeBeforeNoon {

    /** Returns a boolean indicating whether current local time is before or after midday. */
    fun isBeforeNoon(): Boolean
}