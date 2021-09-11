package com.github.m5rian.kommandHandler

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class CommandContext(val event: GuildMessageReceivedEvent, val executor: String) {
}