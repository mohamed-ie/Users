package com.users.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.users.core.database.UsersDatabase
import com.users.core.database.model.AddressEntity
import com.users.core.database.model.CompanyEntity
import com.users.core.database.model.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserDaoTest {
    private lateinit var db: UsersDatabase
    private lateinit var userDao: UserDao

    @Before
    fun createDb() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UsersDatabase::class.java).build()
        userDao = db.userDao
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun insertUserAndRetrieveUser() = runTest {
        userDao.insert(user = user, company = company, address = address)

        // Retrieve user
        val expectedUsers = userDao.users().first()
        assertTrue(expectedUsers.isNotEmpty())

        // Retrieve user ids
        val expectedUserIds = userDao.userIds.first()
        assertTrue(expectedUserIds.isNotEmpty())
    }

    @Test
    fun testDeleteUser() = runTest {
        userDao.insert(user = user, company = company, address = address)

        val userId = userDao.userIds.first()[0]
        userDao.deleteUser(userId)

        val expectedUsers = userDao.users().first()

        assertTrue(expectedUsers.isEmpty())
    }
}

private val user
    get() = UserEntity(
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org"
    )

private val address
    get() = AddressEntity(
        street = "Kulas Light",
        suite = "Apt. 556",
        city = "Gwenborough",
        zipcode = "92998-3874",
        latitude = -37.3159,
        longitude = 81.1496
    )

private val company
    get() = CompanyEntity(
        name = "Romaguera-Crona",
        catchPhrase = "Multi-layered client-server neural-net",
        businessStuff = "harness real-time e-markets"
    )