package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext

interface Resolver<T> {
    suspend fun resolve(ctx: CommandContext, parameter: String): T?
}