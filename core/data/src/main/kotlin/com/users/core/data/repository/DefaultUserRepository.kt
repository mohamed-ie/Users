package com.users.core.data.repository

import com.users.core.data.model.asEntity
import com.users.core.database.dao.UserDao
import com.users.core.database.model.PopulatedUser
import com.users.core.database.model.asExternalModel
import com.users.core.model.User
import com.users.core.network.datasource.UsersNetworkDataSource
import com.users.core.network.model.UserNetworkModel
import com.users.core.network.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
internal class DefaultUserRepository(
    private val userDao: UserDao,
    private val networkDataSource: UsersNetworkDataSource
) : UserRepository {
    override val likedUsers: Flow<List<User>> = userDao.users()
        .map { it.map(PopulatedUser::asExternalModel) }

    /**
     * A flow of all users, fetched from the network and combined with liked status
     * from the local database.
     *
     * This flow emits [Result] objects containing either a list of users or an error.
     * It first fetches users from the network, handles potential errors, and maps them
     * to external models.
     *
     * It then combines this flow with another flow of liked user IDs from the local
     * database. The combined flow updates each user's `isLiked` status based on
     * whether their ID is present in the liked IDs list.
     *
     * Finally, it wraps the result in a [Result] object to handle success or failure
     * and catches any exceptions.
     */
    override fun users(): Flow<Result<List<User>>> = flow {
        val users = networkDataSource.users()
            .getOrThrow()
            ?.filterNotNull()
            ?.mapNotNull(UserNetworkModel::asExternalModel)
            ?: emptyList()

        emit(users)
    }
        .combine(userDao.userIds) { users, likedIds ->
            users.map { it.copy(isLiked = likedIds.contains(it.id)) }
        }
        .map { users -> Result.success(users) }
        .catch { throwable -> emit(Result.failure(throwable)) }

    override suspend fun like(user: User) {
        userDao.insert(
            user = user.asEntity(),
            company = user.company.asEntity(),
            address = user.address.asEntity()
        )
    }

    override suspend fun dislike(id: Long) {
        userDao.deleteUser(id)
    }
}