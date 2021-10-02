package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

interface Resolver<T> {
    suspend fun resolve(ctx: CommandContext, parameter: String?): Arg<T> {
        return if (parameter == null) Arg.ofNotGiven(ctx)
        else resolveIfNotNull(ctx, parameter)
    }

    suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<T>
}