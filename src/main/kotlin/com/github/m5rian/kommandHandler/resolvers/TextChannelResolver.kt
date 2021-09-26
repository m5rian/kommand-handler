package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.DiscordRegex
import net.dv8tion.jda.api.entities.TextChannel

class TextChannelResolver : Resolver<TextChannel> {
    override suspend fun resolveIfNotNull(ctx: CommandContext, parameter: String): Arg<TextChannel> {
        return when {
            parameter.matches(DiscordRegex.id) || parameter.matches(DiscordRegex.textChannelMention) -> {
                val id = parameter.removePrefix("<#").removeSuffix(">")
                Arg.ofNullable(ctx.guild.getTextChannelById(id))
            }
            else -> Arg.ofNullable(ctx.guild.textChannels.find { it.name.contains(parameter, ignoreCase = true) })
        }
    }
}