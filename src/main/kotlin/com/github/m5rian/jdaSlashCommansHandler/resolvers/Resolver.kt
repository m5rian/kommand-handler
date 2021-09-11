package com.github.m5rian.jdaSlashCommansHandler.resolvers

import java.util.*

interface Resolver<T> {
    fun resolve(parameter: String): Optional<T>
}