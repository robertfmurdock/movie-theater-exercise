package com.jpmc.theater

fun Theater.printSchedule() {
    println(provider.currentDate())
    println("===================================================")
    println(schedule.joinToString("\n", transform = Showing::humanReadableDescription))
    println("===================================================")
}