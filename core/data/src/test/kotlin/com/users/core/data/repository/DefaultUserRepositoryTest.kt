package com.users.core.data.repository

import com.users.core.data.model.asEntity
import com.users.core.database.dao.UserDao
import com.users.core.model.User
import com.users.core.network.datasource.UsersNetworkDataSource
import com.users.core.test.model.networkUsers
import com.users.core.test.model.populatedUsers
import com.users.core.test.model.user
import com.users.core.test.model.users
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DefaultUserRepositoryTest {

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var networkDataSource: UsersNetworkDataSource

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        whenever(userDao.users()).thenReturn(flowOf(populatedUsers(2)))
        userRepository = DefaultUserRepository(userDao, networkDataSource)
    }

    @Test
    fun `likedUsers flow emits data from userDao`() = runTest {
        val expectedUsers = users(2)

        val actualUsers = userRepository.likedUsers.first()

        assertEquals(expectedUsers, actualUsers)
    }

    @Test
    fun `users flow emits data from network and combines with liked status`() = runTest {
        val networkUsers = networkUsers(2)
        val expectedUsers = users(2)
        val ids = expectedUsers.map(User::id)

        whenever(networkDataSource.users()).thenReturn(Result.success(networkUsers))

        whenever(userDao.userIds).thenReturn(flowOf(ids))

        val actualUsers = userRepository.users().first().getOrThrow()

        assertEquals(expectedUsers, actualUsers)
    }

    @Test
    fun `like inserts user and associated data`() = runTest {
        val user = user()

        userRepository.like(user)

        verify(userDao).insert(
            user = user.asEntity(),
            company = user.company.asEntity(),
            address = user.address.asEntity()
        )
    }

    @Test
    fun `dislike deletes user by ID`() = runTest {
        val userId = 1L

        userRepository.dislike(userId)

        verify(userDao).deleteUser(userId)
    }
}