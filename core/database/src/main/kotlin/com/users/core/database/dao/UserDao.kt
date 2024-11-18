package com.users.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.users.core.database.model.AddressEntity
import com.users.core.database.model.CompanyEntity
import com.users.core.database.model.PopulatedUser
import com.users.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM user")
    fun users(): Flow<List<PopulatedUser>>

    @get:Query("SELECT id FROM user")
    val userIds: Flow<List<Long>>

    /**
     * Inserts a [UserEntity] into the database.
     * For internal use only within [insertUser] function.
     */
    @Insert
    suspend fun insertUser(user: UserEntity): Long

    /**
     * Inserts a [CompanyEntity] into the database.
     * For internal use only within [insertUser] function.
     */
    @Insert
    suspend fun insertCompany(company: CompanyEntity)

    /**
     * Inserts an [AddressEntity] into the database.
     * For internal use only within [insertUser] function.
     */
    @Insert
    suspend fun insertAddress(address: AddressEntity)

    /**
     * This function performs an atomic insertion of a [UserEntity], along with their corresponding
     * [CompanyEntity] and [AddressEntity], ensuring data consistency. Since [CompanyEntity] and [AddressEntity] are
     * considered weak entities, their existence depends on the associated [UserEntity]. Therefore,
     * this function handles the insertion of all three entities within a single transaction.
     */
    @Transaction
    suspend fun insert(user: UserEntity, company: CompanyEntity, address: AddressEntity) {
        val userId = insertUser(user)
        val updatedCompany = company.copy(id = userId)
        val updatedAddress = address.copy(id = userId)
        insertCompany(updatedCompany)
        insertAddress(updatedAddress)
    }

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUser(id: Long)
}