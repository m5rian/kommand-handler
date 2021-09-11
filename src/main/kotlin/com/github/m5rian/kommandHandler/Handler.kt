package com.github.m5rian.kommandHandler

import com.github.m5rian.kommandHandler.resolvers.Resolvers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.reflections.Reflections
import java.util.concurrent.ForkJoinPool
import kotlin.reflect.KFunction
import kotlin.reflect.full.*

class Handler : ListenerAdapter() {
    private val cogs: MutableList<Cog> = mutableListOf()
    private val scope = CoroutineScope(ForkJoinPool().asCoroutineDispatcher())
    var commandPackage: String? = null
    var defaultPrefixes: MutableList<String> = mutableListOf()
    var guildPrefixes: ((Guild) -> MutableList<String>)? = null

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        val prefixes: MutableList<String> = guildPrefixes?.invoke(event.guild) ?: defaultPrefixes
        val prefix: String = prefixes.firstOrNull { event.message.contentRaw.startsWith(it) } ?: return

        this.cogs.flatMap { it.commands }.forEach { command ->
            val messageWithoutPrefix: String = event.message.contentRaw.substring(prefix.length)

            val executor: String = mutableListOf(command.name, *command.aliases)
                .filter { it.length <= messageWithoutPrefix.length }
                .firstOrNull { it.equals(messageWithoutPrefix.substring(0, it.length), ignoreCase = true) } ?: return@forEach

            val ctx = CommandContext(event, executor)

            val commandArguments: String = messageWithoutPrefix.substring(executor.length + 1)
            val args: MutableList<String> = commandArguments.split("\\s+".toRegex()).toMutableList()
            args.removeAll { it.isBlank() }

            val cog: Cog = this.cogs.first { command in it.commands }
            scope.launch {
                try {
                    val resolvedArgs: MutableList<Any?> = mutableListOf()
                    command.method.valueParameters.mapIndexed { index, parameter ->
                        if (index == 0) return@mapIndexed
                        resolvedArgs.add(Resolvers.resolve(ctx, parameter, args[index - 1]))
                    }

                    command.method.callSuspend(cog, ctx, *resolvedArgs.toTypedArray())
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun loadCogs() {
        if (commandPackage == null) throw IllegalStateException("Command package is not set!")
        Reflections(commandPackage).getSubTypesOf(Cog::class.java)
            .filter { !it.isInterface }
            .map { it.getDeclaredConstructor().newInstance() }
            .forEach { cog ->
                this.cogs.add(cog)
                loadCommands(cog)
            }
    }

    private fun loadCommands(cog: Cog) {
        cog::class.functions.filter { it.hasAnnotation<Command>() }
            .mapNotNull { loadCommand(it) }
            .let { cog.commands.addAll(it) }
    }

    private fun loadCommand(method: KFunction<*>): CommandImpl? {
        if (method.valueParameters.firstOrNull()?.type?.classifier != CommandContext::class) return null
        val commandInfo = method.findAnnotation<Command>() ?: return null
        return CommandImpl(commandInfo.name, commandInfo.aliases, commandInfo.description, method)
    }
}

fun handler(handler: Handler.() -> Unit) = Handler().apply(handler)