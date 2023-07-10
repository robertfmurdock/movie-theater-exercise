package com.jpmc.theater

import java.time.LocalDateTime

data class Showing(
    val movie: Movie,
    val sequenceOfTheDay: Int,
    val startTime: LocalDateTime
)
