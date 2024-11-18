package com.users.core.data.repository

import com.users.core.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    val likedUsers: Flow<List<User>>

    val users: Flow<Result<List<User>>>

    suspend fun like(user: User)

    suspend fun dislike(id: Long)
}