package com.users.core.database.di

import android.content.Context
import androidx.room.Room
import com.users.core.database.UsersDatabase
import com.users.core.database.UsersDatabase.Companion.DATABASE_NAME
import org.koin.core.annotation.Single

@Single
internal fun usersDatabase(context: Context): UsersDatabase =
    Room.databaseBuilder(context, UsersDatabase::class.java, DATABASE_NAME)
        .build()