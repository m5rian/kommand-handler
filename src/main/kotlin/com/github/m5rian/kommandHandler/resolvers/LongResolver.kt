package com.github.m5rian.kommandHandler.resolvers

import java.util.*

class LongResolver : Resolver<Long> {
    override fun resolve(parameter: String): Optional<Long> {
        return Optional.ofNullable(parameter.toLongOrNull())
    }
}