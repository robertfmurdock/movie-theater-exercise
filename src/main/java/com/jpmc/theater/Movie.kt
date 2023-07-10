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
    private val description: String? = null


    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val movie = o as Movie
        return java.lang.Double.compare(
            movie.ticketPrice,
            ticketPrice
        ) == 0 && title == movie.title && description == movie.description && runningTime == movie.runningTime && specialCode == movie.specialCode
    }

    override fun hashCode(): Int {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode)
    }

    companion object {
        const val MOVIE_CODE_SPECIAL = 1
    }
}

fun Showing.calculateTicketPrice(): Double {
    return movie.ticketPrice - getDiscount(movie)
}

private fun Showing.getDiscount(movie: Movie): Double {
    val specialDiscount = movie.calculateSpecialDiscount()

    val matineeDiscount = calculateMatineeDiscount()

    val sequenceDiscount = sequenceDiscount()

    return listOf(
        specialDiscount,
        matineeDiscount,
        sequenceDiscount
    ).max()
}

private val matineeBegin = LocalTime.of(11, 0)
private val matineeEnd = LocalTime.of(16, 0)

fun Showing.calculateMatineeDiscount() = if (
    startTime.toLocalTime().shouldApplyMatineeDiscount()
) {
    movie.ticketPrice * 0.25
} else {
    0.0
}

private fun LocalTime.shouldApplyMatineeDiscount(): Boolean {
    return this == matineeBegin
            || this == matineeEnd
            || (this.isBetween(matineeBegin, matineeEnd))
}

private fun LocalTime.isBetween(localTime: LocalTime?, localTime1: LocalTime?) =
    isAfter(localTime) && isBefore(localTime1)

private fun Showing.sequenceDiscount(): Double = when (sequenceOfTheDay) {
    1 -> 3.0
    2 -> 2.0
    else -> 0.0
}

private fun Movie.calculateSpecialDiscount() = if (Movie.MOVIE_CODE_SPECIAL == specialCode) {
    ticketPrice * 20.percent
} else {
    0.0
}