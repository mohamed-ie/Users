package com.users.core.data.repository

import com.users.core.data.model.asEntity
import com.users.core.database.dao.UserDao
import com.users.core.database.model.PopulatedUser
import com.users.core.model.Address
import com.users.core.model.Company
import com.users.core.model.Geolocation
import com.users.core.model.User
import com.users.core.network.datasource.UsersNetworkDataSource
import com.users.core.network.model.AddressNetworkModel
import com.users.core.network.model.CompanyNetworkModel
import com.users.core.network.model.GeolocationNetworkModel
import com.users.core.network.model.UserNetworkModel
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

    private lateinit var userRepository: DefaultUserRepository

    @Before
    fun setup() {
        userRepository = DefaultUserRepository(userDao, networkDataSource)
    }

    @Test
    fun `likedUsers flow emits data from userDao`() = runTest {
        val populatedUser = populatedUser
        val expectedUsers = users

        whenever(userDao.users()).thenReturn(flowOf(populatedUser))

        val actualUsers = userRepository.likedUsers.first()

        assertEquals(expectedUsers, actualUsers)
    }

    @Test
    fun `users flow emits data from network and combines with liked status`() = runTest {
        val networkUsers = networkUsers
        val expectedUsers = users
        val ids = expectedUsers.map(User::id)

        whenever(networkDataSource.users()).thenReturn(Result.success(networkUsers))

        whenever(userDao.userIds).thenReturn(flowOf(ids))

        val actualUsers = userRepository.users.first().getOrThrow()

        assertEquals(expectedUsers, actualUsers)
    }

    @Test
    fun `like inserts user and associated data`() = runTest {
        val user = users.first()

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

private val populatedUser
    get() = users.map { user ->
        PopulatedUser(
            user = user.asEntity(),
            company = user.company.asEntity().copy(id = user.id),
            address = user.address.asEntity().copy(id = user.id)
        )
    }

private val users
    get() = List(10) {
        val id = it.toLong()
        user(id)
    }

private fun user(id: Long) = User(
    id = id,
    name = "User $id",
    username = "Username $id",
    email = "user$id@example.com",
    website = "Website $id",
    company = company(id),
    address = address(id),
    phone = "phone $id",
    isLiked = true
)

private fun company(id: Long) = Company(
    name = "Company $id",
    catchPhrase = "Catch phrase $id",
    businessStuff = "Business stuff $id"
)

private fun address(id: Long) = Address(
    street = "Address $id",
    suite = "Suite $id",
    city = "City $id",
    zipcode = "$id",
    geolocation = Geolocation(
        latitude = id.toDouble(),
        longitude = id.toDouble()
    )
)

private val networkUsers
    get() = List(10) {
        val id = it.toLong()
        UserNetworkModel(
            id = id,
            name = "User $id",
            username = "Username $id",
            email = "user$id@example.com",
            website = "Website $id",
            company = networkCompany(id),
            address = networkAddress(id),
            phone = "phone $id"
        )
    }

private fun networkCompany(id: Long) = CompanyNetworkModel(
    name = "Company $id",
    catchPhrase = "Catch phrase $id",
    businessStuff = "Business stuff $id"
)

private fun networkAddress(id: Long) = AddressNetworkModel(
    street = "Address $id",
    suite = "Suite $id",
    city = "City $id",
    zipcode = "$id",
    geolocation = GeolocationNetworkModel(
        latitude = id.toDouble(),
        longitude = id.toDouble()
    )
)