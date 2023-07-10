package com.jpmc.theater

import java.time.Duration
import java.util.concurrent.TimeUnit

fun Duration.humanReadable(): String {
    val hour = toHours()
    val remainingMin = toMinutes() - TimeUnit.HOURS.toMinutes(toHours())
    return String.format(
        "(%s hour%s %s minute%s)",
        hour,
        handlePlural(hour),
        remainingMin,
        handlePlural(remainingMin)
    )
}

// (s) postfix should be added to handle plural correctly
private fun handlePlural(value: Long): String {
    return if (value == 1L) {
        ""
    } else {
        "s"
    }
}