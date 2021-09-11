package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.DiscordRegex
import com.github.m5rian.kommandHandler.utilities.await
import net.dv8tion.jda.api.entities.User

class UserResolver : Resolver<User> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): User? {
        return when {
            parameter.matches(DiscordRegex.userId) -> ctx.bot.retrieveUserById(parameter).await()
            parameter.matches(DiscordRegex.userMention) -> {
                val id = parameter.removePrefix("<@").removePrefix("!").removeSuffix(">")
                ctx.bot.retrieveUserById(id).await()
            }
            else -> null
        }
    }
}