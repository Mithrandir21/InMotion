package com.example.testapp.utils.time

import java.util.*
import javax.inject.Inject

class TimeBeforeNoonImpl @Inject constructor() : TimeBeforeNoon {

    /** Returns a boolean indicating whether current local time is before or after midday. */
    override fun isBeforeNoon(): Boolean = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) in 0..11
}