package com.github.m5rian.kommandHandler.resolvers.impl

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.MultiWord
import com.github.m5rian.kommandHandler.resolvers.Arg
import com.github.m5rian.kommandHandler.resolvers.Resolver

class MultiWordResolver : Resolver<MultiWord> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<MultiWord> {
        return Arg.ofNullable(MultiWord(parameter), ctx)
    }
}