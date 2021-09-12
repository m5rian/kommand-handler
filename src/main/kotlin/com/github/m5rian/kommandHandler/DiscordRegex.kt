package com.github.m5rian.kommandHandler

object DiscordRegex {
    val id = "\\d{18}".toRegex()

    val userMention = "<@!\\d*>".toRegex()
    val userName = ".{2,32}".toRegex()
    val userAsTag = ".{2,32}#\\d{4}".toRegex()

    val roleMention = "<@&\\d*>".toRegex()

    val textChannelMention = "<#\\d*>".toRegex()
}