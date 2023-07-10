package com.jpmc.theater

class Reservation(private val customer: Customer, private val showing: Showing, private val audienceCount: Int) {
    fun totalFee(): Double {
        return showing.movie.ticketPrice * audienceCount
    }
}