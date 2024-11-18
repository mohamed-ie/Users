package com.users.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.users.core.model.Address
import com.users.core.model.Geolocation

@Entity(
    tableName = "address",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class AddressEntity(
    @PrimaryKey
    val id: Long = 0,
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val latitude: Double,
    val longitude: Double
)

fun AddressEntity.asExternalModel() = Address(
    street = street,
    suite = suite,
    city = city,
    zipcode = zipcode,
    geolocation = Geolocation(
        latitude = latitude,
        longitude = longitude
    )
)