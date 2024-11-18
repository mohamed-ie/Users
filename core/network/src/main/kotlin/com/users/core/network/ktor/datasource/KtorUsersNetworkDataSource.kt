package com.users.core.network.ktor.datasource

import com.users.core.network.datasource.UsersNetworkDataSource
import com.users.core.network.ktor.apiCall
import com.users.core.network.model.UserNetworkModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.koin.core.annotation.Single

@Single
internal class KtorUsersNetworkDataSource(
    private val client: HttpClient
) : UsersNetworkDataSource {

    override suspend fun users(): Result<List<UserNetworkModel?>?> =
        client.apiCall {
            get("users")
        }
}