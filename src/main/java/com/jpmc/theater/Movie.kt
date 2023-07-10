package com.jpmc.theater

import java.time.Duration
import java.util.*

class Movie(val title: String, val runningTime: Duration, val ticketPrice: Double, private val specialCode: Int) {
    private val description: String? = null
    fun calculateTicketPrice(showing: Showing): Double {
        return ticketPrice - getDiscount(showing.sequenceOfTheDay)
    }

    private fun getDiscount(showSequence: Int): Double {
        var specialDiscount = 0.0
        if (MOVIE_CODE_SPECIAL == specialCode) {
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
        private const val MOVIE_CODE_SPECIAL = 1
    }
}