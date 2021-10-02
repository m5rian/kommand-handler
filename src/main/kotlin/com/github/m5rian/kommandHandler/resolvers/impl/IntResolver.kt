package com.github.m5rian.kommandHandler.resolvers.impl

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.resolvers.Arg
import com.github.m5rian.kommandHandler.resolvers.Resolver

class IntResolver : Resolver<Int> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Int> {
        return Arg.ofNullable(parameter.toIntOrNull(), ctx)
    }
}