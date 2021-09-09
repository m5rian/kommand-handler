package com.github.m5rian.jdaSlashCommansHandler

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Command(
    val name: String,
    val aliases: Array<String> = [],
    val description: String = "",
    val args: Array<String> = []
)
