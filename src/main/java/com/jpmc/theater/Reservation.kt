package com.jpmc.theater

data class Reservation(
    private val customer: Customer,
    private val showing: Showing,
    private val audienceCount: Int
) {
    fun totalFee(): Double = showing.movie.ticketPrice * audienceCount
}
