package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.MultiWord

class MultiWordResolver : Resolver<MultiWord> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<MultiWord> {
        return Arg.ofNullable(MultiWord(parameter))
    }
}