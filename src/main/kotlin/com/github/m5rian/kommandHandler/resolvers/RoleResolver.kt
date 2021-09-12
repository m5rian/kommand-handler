package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.DiscordRegex
import net.dv8tion.jda.api.entities.Role

class RoleResolver : Resolver<Role> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): Role? {
        return when {
            parameter.matches(DiscordRegex.id) || parameter.matches(DiscordRegex.roleMention) -> {
                val id = parameter.removePrefix("<@&").removeSuffix(">")
                ctx.guild.getRoleById(id)
            }
            else -> ctx.guild.roles.find { it.name.contains(parameter, ignoreCase = true) }
        }
    }
}