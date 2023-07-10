package com.jpmc.theater.discount

import com.jpmc.theater.Showing
import com.jpmc.theater.discount
import java.time.LocalTime


private val matineeBegin = LocalTime.of(11, 0)
private val matineeEnd = LocalTime.of(16, 0)

val Showing.matineeDiscount
    get() = discount(
        rule = startTime.toLocalTime()::shouldApplyMatineeDiscount,
        discount = { movie.ticketPrice * 0.25 }
    )

private fun LocalTime.shouldApplyMatineeDiscount() = this == matineeBegin
        || this == matineeEnd
        || (this.isBetween(matineeBegin, matineeEnd))

private fun LocalTime.isBetween(localTime: LocalTime?, localTime1: LocalTime?) =
    isAfter(localTime) && isBefore(localTime1)