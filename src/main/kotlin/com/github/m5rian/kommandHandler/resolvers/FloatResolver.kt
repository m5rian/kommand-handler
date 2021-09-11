package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

class FloatResolver : Resolver<Float> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): Float? {
        return parameter.replace(",", ".").toFloatOrNull()
    }
}