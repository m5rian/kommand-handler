package com.github.m5rian.kommandHandler.resolvers.impl

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.resolvers.Arg
import com.github.m5rian.kommandHandler.resolvers.Resolver

class LongResolver : Resolver<Long> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<Long> {
        return Arg.ofNullable(parameter.toLongOrNull(), ctx)
    }
}