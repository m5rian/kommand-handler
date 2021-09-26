package com.github.m5rian.kommandHandler.resolvers

data class Arg<T>(val value: T?, val found: Boolean, val given: Boolean) {
    companion object {
        fun <T> ofNullable(value: T?): Arg<T> {
            return if (value == null) Arg(value = value, found = false, given = true)
            else Arg(value = value, found = true, given = true)
        }

        fun <T> ofNotFound(): Arg<T> = Arg(value = null, found = false, given = true)
        fun <T> ofNotGiven(): Arg<T> = Arg(value = null, found = false, given = false)
    }
}
