package com.github.m5rian.kommandHandler

import kotlin.reflect.KFunction

@Suppress("ArrayInDataClass")
internal data class CommandImpl(val name: String, val aliases: Array<String>, val method: KFunction<*>)