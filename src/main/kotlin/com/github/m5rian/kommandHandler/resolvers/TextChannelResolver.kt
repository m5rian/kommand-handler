package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.DiscordRegex
import net.dv8tion.jda.api.entities.TextChannel

class TextChannelResolver : Resolver<TextChannel> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): TextChannel? {
        return when {
            parameter.matches(DiscordRegex.id) || parameter.matches(DiscordRegex.textChannelMention) -> {
                val id = parameter.removePrefix("<#").removeSuffix(">")
                ctx.guild.getTextChannelById(id)
            }
            else -> ctx.guild.textChannels.find { it.name.contains(parameter, ignoreCase = true) }
        }
    }
}