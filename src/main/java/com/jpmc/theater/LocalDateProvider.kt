package com.jpmc.theater

import java.time.LocalDate

class LocalDateProvider {
    fun currentDate(): LocalDate = LocalDate.now()

    companion object {
        val singleton: LocalDateProvider by lazy { LocalDateProvider() }
    }
}
