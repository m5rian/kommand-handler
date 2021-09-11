package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class IntResolver : Resolver<Int> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): Int? {
        return parameter.toIntOrNull()
    }
}