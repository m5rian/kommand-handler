package com.github.m5rian.kommandHandler.utilities

import kotlinx.coroutines.future.await
import net.dv8tion.jda.api.requests.RestAction

suspend fun <T> RestAction<T>.await(): T {
    return this.submit().await()
}