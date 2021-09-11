package com.github.m5rian.jdaSlashCommansHandler.resolvers

import java.util.*

class DoubleResolver : Resolver<Double> {
    override fun resolve(parameter: String): Optional<Double> {
        return Optional.ofNullable(parameter.toDoubleOrNull())
    }
}