package com.users.core.network.model

import com.users.core.model.Address
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressNetworkModel(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    @SerialName("geo")
    val geolocation: GeolocationNetworkModel? = null
)

fun AddressNetworkModel.asExternalModel(): Address? {
    return Address(
        street = street ?: return null,
        suite = suite ?: return null,
        city = city ?: return null,
        zipcode = zipcode ?: return null,
        geolocation = geolocation?.asExternalModel() ?: return null
    )
}