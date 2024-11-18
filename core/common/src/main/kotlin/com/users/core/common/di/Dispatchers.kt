package com.users.core.common.di

import com.users.core.common.UsersDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Single

@Suppress("unused")
@Qualifier
annotation class Dispatcher(val dispatcher: UsersDispatcher)

@Single
@Dispatcher(UsersDispatcher.IO)
internal fun ioDispatcher() = Dispatchers.IO

@Single
@Dispatcher(UsersDispatcher.Default)
internal fun defaultDispatcher() = Dispatchers.Default