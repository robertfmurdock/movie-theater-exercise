package com.jpmc.theater.discount

import com.jpmc.theater.Showing
import com.jpmc.theater.discount

val Showing.firstShowDiscount
    get() = discount(
        rule = { sequenceOfTheDay == 1 },
        discount = { 3.0 }
    )
val Showing.secondShowDiscount
    get() = discount(
        rule = { sequenceOfTheDay == 2 },
        discount = { 2.0 }
    )