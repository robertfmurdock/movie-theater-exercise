package com.jpmc.theater

import com.fasterxml.jackson.databind.ObjectMapper
import com.jpmc.theater.LocalDateProvider.Companion.singleton
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.TimeUnit

class Theater(private val provider: LocalDateProvider) {
    private val schedule: List<Showing>

    init {
        val spiderMan = Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1)
        val turningRed = Movie("Turning Red", Duration.ofMinutes(85), 11.0, 0)
        val theBatMan = Movie("The Batman", Duration.ofMinutes(95), 9.0, 0)
        schedule = listOf(
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
    }

    fun reserve(customer: Customer, sequence: Int, howManyTickets: Int): Reservation {
        val showing: Showing = try {
            schedule[sequence - 1]
        } catch (ex: RuntimeException) {
            ex.printStackTrace()
            throw IllegalStateException("not able to find any showing for given sequence $sequence")
        }
        return Reservation(customer, showing, howManyTickets)
    }

    fun printSchedule() {
        println(provider.currentDate())
        println("===================================================")
        println(schedule.joinToString("\n", transform = Showing::humanReadableShowingDescription))
        println("===================================================")
    }


    private val mapper = ObjectMapper()

    fun jsonSchedule() = mapper.writeValueAsString(mapOf(
        "currentDate" to "${provider.currentDate()}",
        "schedule" to schedule.map {
            mapOf(
                "listing" to it.sequenceOfTheDay,
                "startTime" to "${it.startTime.toLocalTime()}",
                "title" to it.movie.title,
                "runningTime" to humanReadableFormat(it.movie.runningTime),
                "ticketPrice" to it.movie.ticketPrice,
            )
        }
    ))
}

fun main(args: Array<String>) {
    val theater = Theater(singleton)

    if (args.contains("--json")) {
        println(theater.jsonSchedule())
    } else {
        theater.printSchedule()
    }
}

fun Showing.humanReadableShowingDescription() =
    "$sequenceOfTheDay: $startTime ${movie.title} ${humanReadableFormat(movie.runningTime)} $${movie.ticketPrice}"

fun humanReadableFormat(duration: Duration): String {
    val hour = duration.toHours()
    val remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours())
    return String.format(
        "(%s hour%s %s minute%s)",
        hour,
        handlePlural(hour),
        remainingMin,
        handlePlural(remainingMin)
    )
}

// (s) postfix should be added to handle plural correctly
private fun handlePlural(value: Long): String {
    return if (value == 1L) {
        ""
    } else {
        "s"
    }
}