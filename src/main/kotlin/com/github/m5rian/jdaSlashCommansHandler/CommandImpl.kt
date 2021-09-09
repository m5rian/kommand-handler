package com.github.m5rian.jdaSlashCommansHandler

import kotlin.reflect.KFunction

internal data class CommandImpl(val name: String, val aliases: Array<String>, val description: String, val method: KFunction<*>)