package com.jpmc.theater

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TheaterTests {
    @Test
    fun totalFeeForCustomer() {
        val theater = Theater(LocalDateProvider.singleton)
        val john = Customer("John Doe", "id-12345")
        val reservation = theater.reserve(john, 2, 4)
        //        System.out.println("You have to pay " + reservation.getTotalFee());
        Assertions.assertEquals(reservation.totalFee(), 50.0)
    }

    @Test
    fun printMovieSchedule() {
        val theater = Theater(LocalDateProvider.singleton)
        theater.printSchedule()
    }
}
