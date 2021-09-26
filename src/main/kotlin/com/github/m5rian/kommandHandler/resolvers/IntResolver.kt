package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class IntResolver : Resolver<Int> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Int> {
        return Arg.ofNullable(parameter.toIntOrNull())
    }
}