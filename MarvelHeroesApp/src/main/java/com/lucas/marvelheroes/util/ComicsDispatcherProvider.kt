package com.lucas.marvelheroes.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * The reason why I've made it an interface is that, if I want to test it, I could provide different
 * dispatchers to the ViewModel and ensure that it is using the correct one.
 */
interface ComicsDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}