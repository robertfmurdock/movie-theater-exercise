package com.jpmc.theater

fun main(args: Array<String>) {
    val theater = Theater(LocalDateProvider.singleton)

    if (args.contains("--json")) {
        println(theater.jsonSchedule())
    } else {
        theater.printSchedule()
    }
}