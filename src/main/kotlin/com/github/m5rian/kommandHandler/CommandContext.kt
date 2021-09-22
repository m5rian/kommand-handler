package com.github.m5rian.kommandHandler

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.*
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import kotlin.reflect.KFunction

@Suppress("unused")
class CommandContext(
    _event: GuildMessageReceivedEvent,
    _method: KFunction<*>,
    _member: Member,
    _executor: String
) {
    @Suppress("MemberVisibilityCanBePrivate")
    val event: GuildMessageReceivedEvent = _event
    val executor: String = _executor
    val method: KFunction<*> = _method

    val bot: JDA = event.jda
    val botMember: Member = event.guild.selfMember

    val guild: Guild = event.guild
    val member: Member = _member
    val channel: TextChannel = event.channel
    val message: Message = event.message

    val user: User = event.author
}