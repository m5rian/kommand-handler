package com.github.m5rian.kommandHandler.resolvers

import com.github.m5rian.kommandHandler.CommandContext
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Role
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User
import kotlin.reflect.KClassifier
import kotlin.reflect.KParameter

object Resolvers {

    private val map: MutableMap<KClassifier, Resolver<*>> = mutableMapOf()

    fun registerDefaultResolvers() {
        map[Int::class] = IntResolver()
        map[Long::class] = LongResolver()
        map[Float::class] = FloatResolver()
        map[Double::class] = DoubleResolver()

        map[User::class] = UserResolver()
        map[Role::class] = RoleResolver()
        map[Member::class] = MemberResolver()
        map[TextChannel::class] = TextChannelResolver()
    }

    suspend fun resolve(ctx: CommandContext, parameter: KParameter, argument: String?): Any {
        val clazz= parameter.type.arguments.first().type?.classifier
        val resolver = map[clazz] ?: throw Exception("The type ${parameter::class.java.genericSuperclass.typeName} hasn't a registered resolver")
        return resolver.resolve(ctx, argument)
    }
}
