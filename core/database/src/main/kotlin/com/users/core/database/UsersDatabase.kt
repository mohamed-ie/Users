package com.users.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.users.core.database.dao.UserDao
import com.users.core.database.model.AddressEntity
import com.users.core.database.model.CompanyEntity
import com.users.core.database.model.UserEntity

@Database(
    entities = [
        UserEntity::class,
        AddressEntity::class,
        CompanyEntity::class
    ],
    version = 1
)
internal abstract class UsersDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        internal const val DATABASE_NAME = "users_database"
    }
}