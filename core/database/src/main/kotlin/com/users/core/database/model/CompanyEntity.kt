package com.users.core.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.users.core.model.Company

@Entity(
    tableName = "company",
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
data class CompanyEntity(
    @PrimaryKey
    val id: Long = 0,
    val name: String,
    val catchPhrase: String,
    val businessStuff: String
)

fun CompanyEntity.asExternalModel() = Company(
    name = name,
    catchPhrase = catchPhrase,
    businessStuff = businessStuff
)