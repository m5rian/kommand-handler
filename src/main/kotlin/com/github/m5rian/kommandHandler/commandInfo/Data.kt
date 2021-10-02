package com.github.m5rian.kommandHandler.commandInfo

import kotlin.reflect.KClass

annotation class Data(
    val optional: Boolean,
    val type: KClass<*>,
    val description: String = ""
)