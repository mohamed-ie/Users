package com.users.core.database.di

import com.users.core.database.UsersDatabase

import org.koin.core.annotation.Single

@Single
internal fun userDao(usersDatabase:UsersDatabase) = usersDatabase.userDao