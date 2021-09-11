package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class LongResolver : Resolver<Long> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): Long? {
        return parameter.toLongOrNull()
    }
}