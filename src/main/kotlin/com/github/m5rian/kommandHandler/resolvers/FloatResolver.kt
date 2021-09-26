package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class FloatResolver : Resolver<Float> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Float> {
        return Arg.ofNullable(parameter.replace(",", ".").toFloatOrNull())
    }
}