package com.github.m5rian.jdaSlashCommansHandler.resolvers

import java.util.*

class FloatResolver : Resolver<Float> {
    override fun resolve(parameter: String): Optional<Float> {
        return Optional.ofNullable(parameter.toFloatOrNull())
    }
}