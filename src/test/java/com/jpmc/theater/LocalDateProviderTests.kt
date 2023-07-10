package com.jpmc.theater

import org.junit.jupiter.api.Test

class LocalDateProviderTests {
    @Test
    fun makeSureCurrentTime() {
        println("current time is - " + LocalDateProvider.singleton.currentDate())
    }
}
