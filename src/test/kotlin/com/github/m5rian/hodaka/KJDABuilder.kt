package com.github.m5rian.hodaka

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag

class KJDABuilder {
    var token: String? = null
    var listeners: List<EventListener> = emptyList()
    var intents: List<GatewayIntent> = emptyList()
    var cache: List<CacheFlag> = emptyList()
    var activity: Activity? = null
    var chunkFilter: ChunkingFilter? = null
    var memberCachePolicy: MemberCachePolicy? = null
}

fun jda(b: KJDABuilder.() -> Unit): JDABuilder {
    val info = KJDABuilder().apply(b)
    if (info.token == null) throw IllegalStateException("Token cannot be null!")
    return JDABuilder.createLight(info.token).apply {
        addEventListeners(*info.listeners.toTypedArray())
        enableCache(info.cache)
        if (info.activity != null) setActivity(info.activity)
        if (info.chunkFilter != null) setChunkingFilter(info.chunkFilter)
        if (info.memberCachePolicy != null) setMemberCachePolicy(info.memberCachePolicy)
    }
}