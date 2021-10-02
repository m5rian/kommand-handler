package com.github.m5rian.kommandHandler

import com.github.m5rian.kommandHandler.commandInfo.Data
import kotlin.reflect.KFunction

@Suppress("ArrayInDataClass")
internal data class CommandImpl(val name: String, val aliases: Array<String>, val args: Array<Data>, val description: String, val method: KFunction<*>)