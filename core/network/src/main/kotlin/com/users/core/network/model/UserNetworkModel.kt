package com.users.core.network.model

import com.users.core.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserNetworkModel(
    val id: Long? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: AddressNetworkModel? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: CompanyNetworkModel? = null
)

fun UserNetworkModel.asExternalModel(): User?{
    return User(
        id = id ?: return null,
        name = name ?: return null,
        username = username ?: return null,
        email = email ?: return null,
        address = address?.asExternalModel() ?: return null,
        phone = phone ?: return null,
        website = website ?: return null,
        company = company?.asExternalModel() ?: return null,
        isLiked = false
    )
}