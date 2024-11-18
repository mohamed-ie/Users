package com.users.core.network.model

import com.users.core.model.Geolocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeolocationNetworkModel(
    @SerialName("lat")
    val latitude: Double? = null,
    @SerialName("lng")
    val longitude: Double? = null
)

fun GeolocationNetworkModel.asExternalModel(): Geolocation? {
    return Geolocation(
        latitude = latitude ?: return null,
        longitude = longitude ?: return null
    )
}