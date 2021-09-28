package com.github.m5rian.kommandHandler.resolvers

data class Arg<T>(private val _value: T?, val found: Boolean, val given: Boolean) {
    val value: T get() = _value!!

    companion object {
        fun <T> ofNullable(value: T?): Arg<T> {
            return if (value == null) Arg(_value = value, found = false, given = true)
            else Arg(_value = value, found = true, given = true)
        }

        fun <T> ofNotFound(): Arg<T> = Arg(_value = null, found = false, given = true)
        fun <T> ofNotGiven(): Arg<T> = Arg(_value = null, found = false, given = false)
    }
}
