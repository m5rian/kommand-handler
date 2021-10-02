package com.github.m5rian.kommandHandler.resolvers.impl

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.DiscordRegex
import com.github.m5rian.kommandHandler.resolvers.Arg
import com.github.m5rian.kommandHandler.resolvers.Resolver
import com.github.m5rian.kommandHandler.utilities.await
import net.dv8tion.jda.api.entities.User

class UserResolver : Resolver<User> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<User> {
        return when {
            parameter.matches(DiscordRegex.id) || parameter.matches(DiscordRegex.userMention) -> {
                val id = parameter.removePrefix("<@").removePrefix("!").removeSuffix(">")
                Arg.ofNullable(ctx.bot.retrieveUserById(id).await(), ctx)
            }
            parameter.matches(DiscordRegex.userAsTag) -> Arg.ofNullable(ctx.bot.userCache.find {
                val userName = parameter.split("#")[0]
                val userDiscriminator = parameter.split("#")[1]

                it.name == userName && it.discriminator == userDiscriminator
            }, ctx)
            parameter.matches(DiscordRegex.userName) -> Arg.ofNullable(ctx.bot.userCache.find { it.name == parameter }, ctx)
            else -> Arg.ofNotFound(ctx)
        }
    }
}