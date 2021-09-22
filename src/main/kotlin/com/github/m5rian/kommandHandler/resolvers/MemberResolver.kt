package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import com.github.m5rian.kommandHandler.DiscordRegex
import com.github.m5rian.kommandHandler.utilities.await
import net.dv8tion.jda.api.entities.Member

class MemberResolver : Resolver<Member> {
    override suspend fun resolve(ctx: CommandContext, parameter: String): Member? {
        return when {
            parameter.matches(DiscordRegex.id) || parameter.matches(DiscordRegex.userMention) -> {
                val id = parameter.removePrefix("<@").removePrefix("!").removeSuffix(">")
                ctx.guild.retrieveMemberById(id).await()
            }
            parameter.matches(DiscordRegex.userAsTag) -> ctx.guild.memberCache.find {
                val userName = parameter.split("#")[0]
                val userDiscriminator = parameter.split("#")[1]

                val nameMatches = it.effectiveName == userName || it.user.name == userName
                nameMatches && it.user.discriminator == userDiscriminator
            }
            parameter.matches(DiscordRegex.userName) -> ctx.guild.memberCache.find {  it.effectiveName == parameter || it.user.name == parameter }
            else -> null
        }
    }
}