package com.jpmc.theater.discount

import com.jpmc.theater.Showing
import com.jpmc.theater.discount

val Showing.luckyDayDiscount
    get() = discount(
        rule = ::isLuckyDay,
        discount = { 1.0 }
    )

private fun Showing.isLuckyDay() = startTime.toLocalDate().dayOfMonth == 7