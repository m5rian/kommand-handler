package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class DoubleResolver : Resolver<Double> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Double> {
        return Arg.ofNullable(parameter.replace(",", ".").toDoubleOrNull())
    }
}