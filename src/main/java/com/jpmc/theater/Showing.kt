package com.jpmc.theater

import java.time.LocalDateTime

data class Showing(val movie: Movie, val sequenceOfTheDay: Int, val startTime: LocalDateTime) {

    fun isSequence(sequence: Int): Boolean {
        return sequenceOfTheDay == sequence
    }

    val movieFee: Double
        get() = movie.ticketPrice

    private fun calculateFee(audienceCount: Int): Double {
        return this.calculateTicketPrice() * audienceCount
    }
}
