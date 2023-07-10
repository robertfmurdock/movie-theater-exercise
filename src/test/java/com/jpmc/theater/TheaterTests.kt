package com.jpmc.theater

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TheaterTests {
    
    @Test
    fun totalFeeForCustomer() {
        val theater = Theater(LocalDateProvider.singleton)
        val john = Customer("John Doe", "id-12345")
        val reservation = theater.reserve(john, 2, 4)
        assertEquals(reservation.totalFee(), 50.0)
    }

    @Test
    fun printMovieSchedule() {
        val theater = Theater(LocalDateProvider.singleton)
        theater.printSchedule()
    }

    @Test
    fun jsonMovieSchedule() {
        val theater = Theater(LocalDateProvider.singleton)
        val mapper = ObjectMapper()
        val jsonSchedule = theater.jsonSchedule()
        println(jsonSchedule)
        mapper.readTree(jsonSchedule)
    }
}
