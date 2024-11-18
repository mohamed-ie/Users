package com.users.core.network.model

import com.users.core.model.Company
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyNetworkModel(
    val name: String? = null,
    val catchPhrase: String? = null,
    @SerialName("bs")
    val businessStuff: String? = null
)

fun CompanyNetworkModel.asExternalModel(): Company? {
    return Company(
        name = name ?: return null,
        catchPhrase = catchPhrase ?: return null,
        businessStuff = businessStuff ?: return null
    )
}