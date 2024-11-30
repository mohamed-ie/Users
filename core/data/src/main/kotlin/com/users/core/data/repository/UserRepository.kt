package com.users.core.data.repository

import com.users.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val likedUsers: Flow<List<User>>

    fun users(): Flow<Result<List<User>>>

    suspend fun like(user: User)

    suspend fun dislike(id: Long)
}