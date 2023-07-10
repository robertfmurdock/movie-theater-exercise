package com.jpmc.theater

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieTests {
    private val stableDate = LocalDate.of(2023, 1, 1)

    @Test
    fun ticketPricesForSpecialMoveHas20PercentDiscount() {
        val spiderMan = Movie(
            title = "Spider-Man: No Way Home",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 12.5,
            specialCode = 1
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 5,
            startTime = LocalDateTime.of(stableDate, LocalTime.of(20, 0))
        )
        assertEquals(10.0, showing.calculateTicketPrice())
    }

    @Test
    fun ticketPriceForRegularMoveHasNoDiscount() {
        val spiderMan = Movie(
            title = "Spider-Man: Beyond the Spiderverse",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 12.5,
            specialCode = 0
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 5,
            startTime = LocalDateTime.of(stableDate, LocalTime.of(20, 0))
        )
        assertEquals(spiderMan.ticketPrice, showing.calculateTicketPrice())
    }

    @Test
    fun ticketPriceForRegularMoveFirstShowingReducedBy3() {
        val spiderMan = Movie(
            title = "Spider-Man: Homecoming",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 12.5,
            specialCode = 0
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 1,
            startTime = LocalDateTime.of(stableDate, LocalTime.of(20, 0))
        )
        assertEquals(spiderMan.ticketPrice - 3, showing.calculateTicketPrice())
    }

    @Test
    fun ticketPriceForRegularMoveSecondShowingReducedBy2() {
        val spiderMan = Movie(
            title = "Spider-Man: Into the Spiderverse",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 12.5,
            specialCode = 0
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 2,
            startTime = LocalDateTime.of(stableDate, LocalTime.of(20, 0))
        )
        assertEquals(spiderMan.ticketPrice - 2, showing.calculateTicketPrice())
    }


    @Test
    fun ticketPriceForMatineeUpperBoundGets25PercentDiscount() {
        val spiderMan = Movie(
            title = "Spider-Man: Into the Spiderverse",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 100.0,
            specialCode = 0
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 7,
            startTime = LocalDateTime.of(stableDate, LocalTime.of(11, 0))
        )
        assertEquals(75.0, showing.calculateTicketPrice())
    }

    @Test
    fun ticketPriceForMatineeLowerBoundGets25PercentDiscount() {
        val spiderMan = Movie(
            title = "Spider-Man: Into the Spiderverse",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 100.0,
            specialCode = 0
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 7,
            startTime = LocalDateTime.of(stableDate, LocalTime.of(16, 0))
        )
        assertEquals(75.0, showing.calculateTicketPrice())
    }

    @Test
    fun ticketPriceForMatineeMiddleGets25PercentDiscount() {
        val spiderMan = Movie(
            title = "Spider-Man: Into the Spiderverse",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 100.0,
            specialCode = 0
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 7,
            startTime = LocalDateTime.of(stableDate, LocalTime.of(12, 0))
        )
        assertEquals(75.0, showing.calculateTicketPrice())
    }

    @Test
    fun ticketPriceForMovieOnThe7thOfMonthGets1DollarDiscount() {
        val spiderMan = Movie(
            title = "Spider-Man 2: The Good One",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 100.0,
            specialCode = 0
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 7,
            startTime = LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(20, 0))
        )
        assertEquals(99.0, showing.calculateTicketPrice())
    }

    @Test
    fun ticketPriceWithManyDiscountsPrefersBiggest() {
        val spiderMan = Movie(
            title = "Spider-Man 2: The Good One",
            runningTime = Duration.ofMinutes(90),
            ticketPrice = 11.0,
            specialCode = 1
        )
        val showing = Showing(
            movie = spiderMan,
            sequenceOfTheDay = 1,
            startTime = LocalDateTime.of(LocalDate.of(2023, 1, 7), LocalTime.of(11, 0))
        )
        assertEquals(8.0, showing.calculateTicketPrice())
    }
}
