package com.jpmc.theater

import java.util.*

class Customer// NOTE - id is not used anywhere at the moment
/**
 * @param name customer name
 * @param id customer id
 */(private val name: String, private val id: String) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Customer) return false
        val customer = o
        return name == customer.name && id == customer.id
    }

    override fun hashCode(): Int {
        return Objects.hash(name, id)
    }

    override fun toString(): String {
        return "name: $name"
    }
}