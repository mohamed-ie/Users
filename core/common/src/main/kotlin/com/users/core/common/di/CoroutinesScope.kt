package com.users.core.common.di

import com.users.core.common.UsersDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Single

@Qualifier
annotation class ApplicationScope


@ApplicationScope
@Single
internal fun applicationScope(@Dispatcher(UsersDispatcher.Default) defaultDispatcher: CoroutineDispatcher) =
    CoroutineScope(defaultDispatcher + SupervisorJob())