package com.github.m5rian.kommandHandler

object DiscordRegex {

    val userMention = "<@!\\d{18}>".toRegex()
    val userId = "\\d{18}".toRegex()

}