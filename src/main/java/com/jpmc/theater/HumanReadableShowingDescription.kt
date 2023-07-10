package com.jpmc.theater

fun Showing.humanReadableDescription() =
    "$sequenceOfTheDay: $startTime ${movie.title} ${movie.runningTime.humanReadable()} $${movie.ticketPrice}"