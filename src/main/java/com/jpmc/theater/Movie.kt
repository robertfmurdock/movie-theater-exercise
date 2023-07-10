package com.jpmc.theater

import java.time.Duration
import java.util.*

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
    return movie.ticketPrice - movie.getDiscount(sequenceOfTheDay)
}

private fun Movie.getDiscount(showSequence: Int): Double {
    var specialDiscount = 0.0
    if (Movie.MOVIE_CODE_SPECIAL == specialCode) {
        specialDiscount = ticketPrice * 0.2 // 20% discount for special movie
    }
    var sequenceDiscount = 0.0
    if (showSequence == 1) {
        sequenceDiscount = 3.0 // $3 discount for 1st show
    } else if (showSequence == 2) {
        sequenceDiscount = 2.0 // $2 discount for 2nd show
    }
    //        else {
//            throw new IllegalArgumentException("failed exception");
//        }

    // biggest discount wins
    return if (specialDiscount > sequenceDiscount) specialDiscount else sequenceDiscount
}