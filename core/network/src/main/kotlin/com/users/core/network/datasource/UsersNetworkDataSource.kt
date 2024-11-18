package com.users.core.network.datasource

import com.users.core.network.model.UserNetworkModel

interface UsersNetworkDataSource {
    suspend fun users(): Result<List<UserNetworkModel?>?>
}