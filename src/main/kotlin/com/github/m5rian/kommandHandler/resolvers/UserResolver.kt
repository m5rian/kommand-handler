package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.DiscordRegex
import com.github.m5rian.kommandHandler.utilities.await
import net.dv8tion.jda.api.entities.User

class UserResolver : Resolver<User> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): User? {
        return when {
            parameter.matches(DiscordRegex.id) || parameter.matches(DiscordRegex.userMention) -> {
                val id = parameter.removePrefix("<@").removePrefix("!").removeSuffix(">")
                ctx.bot.retrieveUserById(id).await()
            }
            parameter.matches(DiscordRegex.userAsTag) -> ctx.bot.userCache.find {
                val userName = parameter.split("#")[0]
                val userDiscriminator = parameter.split("#")[1]

                it.name == userName && it.discriminator == userDiscriminator
            }
            parameter.matches(DiscordRegex.userName) -> ctx.bot.userCache.find { it.name == parameter }
            else -> null
        }
    }
}