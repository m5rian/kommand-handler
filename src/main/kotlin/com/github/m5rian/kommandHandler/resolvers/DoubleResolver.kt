package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class DoubleResolver : Resolver<Double> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): Double? {
        return parameter.replace(",", ".").toDoubleOrNull()
    }
}