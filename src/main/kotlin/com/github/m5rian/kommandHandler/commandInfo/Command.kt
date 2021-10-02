package com.github.m5rian.kommandHandler.commandInfo

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Command(
    val name: String,
    val aliases: Array<String> = [],
    val description: String = "",
    val args: Array<Data>
)
