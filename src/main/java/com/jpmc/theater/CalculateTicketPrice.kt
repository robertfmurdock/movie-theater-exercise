package com.jpmc.theater

import com.jpmc.theater.discount.firstShowDiscount
import com.jpmc.theater.discount.luckyDayDiscount
import com.jpmc.theater.discount.matineeDiscount
import com.jpmc.theater.discount.secondShowDiscount
import com.jpmc.theater.discount.specialDiscount

fun Showing.calculateTicketPrice() = movie.ticketPrice - biggestDiscount()

val Int.percent: Double
    get() = this / 100.0

private fun Showing.biggestDiscount() = listOfNotNull(
    specialDiscount,
    matineeDiscount,
    firstShowDiscount,
    secondShowDiscount,
    luckyDayDiscount,
).maxOrNull() ?: 0.0

fun discount(rule: () -> Boolean, discount: () -> Double) = if (rule()) {
    discount()
} else {
    null
}
