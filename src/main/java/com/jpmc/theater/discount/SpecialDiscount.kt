package com.jpmc.theater.discount

import com.jpmc.theater.Movie
import com.jpmc.theater.Showing
import com.jpmc.theater.discount
import com.jpmc.theater.percent

val Showing.specialDiscount
    get() = discount(
        rule = { Movie.MOVIE_CODE_SPECIAL == movie.specialCode },
        discount = { movie.ticketPrice * 20.percent }
    )