package com.github.m5rian.jdaSlashCommansHandler.resolvers

import kotlin.reflect.KClassifier
import kotlin.reflect.KParameter

object Resolvers {

    private val map: MutableMap<KClassifier, Resolver<*>> = mutableMapOf()

    fun registerDefaultResolvers() {
        map[Int::class] = IntResolver()
        map[Long::class] = LongResolver()
        map[Float::class] = FloatResolver()
        map[Double::class] = DoubleResolver()
    }

    fun resolve(parameter: KParameter, argument: String): Any? {
        val resolvedArgument = map[parameter.type.classifier]?.resolve(argument) ?: throw Exception("The type ${parameter.type.classifier} hasn't a registered resolver")

        return if (resolvedArgument.isPresent) resolvedArgument.get() else null
    }

}