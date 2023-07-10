package com.jpmc.theater

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

val spiderMan = Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1)
val turningRed = Movie("Turning Red", Duration.ofMinutes(85), 11.0, 0)
val theBatMan = Movie("The Batman", Duration.ofMinutes(95), 9.0, 0)

class Theater(val provider: LocalDateProvider) {

    val schedule = listOf(
        Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
        Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
        Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
        Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
        Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
        Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
        Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
        Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
        Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
    )

    fun reserve(customer: Customer, sequence: Int, howManyTickets: Int): Reservation {
        val showing: Showing = try {
            schedule[sequence - 1]
        } catch (ex: RuntimeException) {
            ex.printStackTrace()
            throw IllegalStateException("not able to find any showing for given sequence $sequence")
        }
        return Reservation(customer, showing, howManyTickets)
    }

}
