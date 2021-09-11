package com.github.m5rian.jdaSlashCommansHandler.resolvers

import java.util.*

class IntResolver : Resolver<Int> {
    override fun resolve(parameter: String): Optional<Int> {
        return Optional.ofNullable(parameter.toIntOrNull())
    }
}