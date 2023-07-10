package com.jpmc.theater

import com.fasterxml.jackson.databind.ObjectMapper

private val mapper = ObjectMapper()
fun Theater.jsonSchedule(): String = mapper.writeValueAsString(mapOf(
    "currentDate" to "${provider.currentDate()}",
    "schedule" to schedule.map {
        mapOf(
            "listing" to it.sequenceOfTheDay,
            "startTime" to "${it.startTime.toLocalTime()}",
            "title" to it.movie.title,
            "runningTime" to it.movie.runningTime.humanReadable(),
            "ticketPrice" to it.movie.ticketPrice,
        )
    }
))