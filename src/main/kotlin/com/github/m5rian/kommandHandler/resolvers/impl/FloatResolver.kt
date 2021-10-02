package com.github.m5rian.kommandHandler.resolvers.impl

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.resolvers.Arg
import com.github.m5rian.kommandHandler.resolvers.Resolver

class FloatResolver : Resolver<Float> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Float> {
        return Arg.ofNullable(parameter.replace(",", ".").toFloatOrNull(), ctx)
    }
}