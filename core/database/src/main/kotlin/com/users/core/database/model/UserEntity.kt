package com.users.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Long = 0,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)