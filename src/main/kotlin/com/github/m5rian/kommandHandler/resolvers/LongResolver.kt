package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class LongResolver : Resolver<Long> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Long> {
        return Arg.ofNullable(parameter.toLongOrNull())
    }
}