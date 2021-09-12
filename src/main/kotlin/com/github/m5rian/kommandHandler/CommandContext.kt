package com.github.m5rian.kommandHandler

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class CommandContext(val event: GuildMessageReceivedEvent, val executor: String) {

    val bot: JDA = event.jda
    val botMember: Member = event.guild.selfMember
    val guild: Guild = event.guild

}