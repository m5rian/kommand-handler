package com.github.m5rian.kommandHandler.resolvers.impl

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.resolvers.Arg
import com.github.m5rian.kommandHandler.resolvers.Resolver

class DoubleResolver : Resolver<Double> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Double> {
        return Arg.ofNullable(parameter.replace(",", ".").toDoubleOrNull(), ctx)
    }
}