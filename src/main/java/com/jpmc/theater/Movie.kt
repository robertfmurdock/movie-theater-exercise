package com.jpmc.theater

import java.time.Duration
import java.time.LocalTime
import java.util.*

private val Int.percent: Double
    get() = this / 100.0

data class Movie(
    val title: String,
    val runningTime: Duration,
    val ticketPrice: Double,
    val specialCode: Int
) {

    companion object {
        const val MOVIE_CODE_SPECIAL = 1
    }
}

fun Showing.calculateTicketPrice() = movie.ticketPrice - biggestDiscount()

private fun Showing.biggestDiscount() = listOfNotNull(
    specialDiscount(),
    matineeDiscount(),
    firstShowDiscount(),
    secondShowDiscount(),
    luckyDayDiscount(),
).maxOrNull() ?: 0.0

private val matineeBegin = LocalTime.of(11, 0)
private val matineeEnd = LocalTime.of(16, 0)

fun discount(rule: () -> Boolean, discount: () -> Double) = if (rule()) {
    discount()
} else {
    null
}

fun Showing.luckyDayDiscount() = discount(
    rule = ::isLuckyDay,
    discount = { 1.0 }
)

private fun Showing.isLuckyDay() = startTime.toLocalDate().dayOfMonth == 7

fun Showing.matineeDiscount() = discount(
    rule = startTime.toLocalTime()::shouldApplyMatineeDiscount,
    discount = { movie.ticketPrice * 0.25 }
)

private fun LocalTime.shouldApplyMatineeDiscount() = this == matineeBegin
        || this == matineeEnd
        || (this.isBetween(matineeBegin, matineeEnd))

private fun LocalTime.isBetween(localTime: LocalTime?, localTime1: LocalTime?) =
    isAfter(localTime) && isBefore(localTime1)

private fun Showing.firstShowDiscount() = discount(
    rule = { sequenceOfTheDay == 1 },
    discount = { 3.0 }
)

private fun Showing.secondShowDiscount() = discount(
    rule = { sequenceOfTheDay == 2 },
    discount = { 2.0 }
)

private fun Showing.specialDiscount() = discount(
    rule = { Movie.MOVIE_CODE_SPECIAL == movie.specialCode },
    discount = { movie.ticketPrice * 20.percent }
)
