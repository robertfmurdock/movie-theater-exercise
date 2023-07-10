package com.jpmc.theater

import java.time.Duration

data class Movie(
    val title: String,
    val runningTime: Duration,
    val ticketPrice: Double,
    val specialCode: Int
) {

    companion object {
        const val MOVIE_CODE_SPECIAL = 1
    }
}
