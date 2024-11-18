package com.users.core.network.ktor.datasource

import com.users.core.network.datasource.UsersNetworkDataSource
import com.users.core.network.di.ktorClient
import com.users.core.network.model.AddressNetworkModel
import com.users.core.network.model.CompanyNetworkModel
import com.users.core.network.model.GeolocationNetworkModel
import com.users.core.network.model.UserNetworkModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class KtorUsersNetworkDataSourceTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var subject: UsersNetworkDataSource

    @Before
    fun setup() {
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(jsonContent),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        subject = KtorUsersNetworkDataSource(ktorClient(mockEngine))
    }

    @Test
    fun testDeserializationOfUserNetwork() = runTest(testDispatcher) {
        val expected = listOf(user)

        val actual = subject.users().getOrNull()

        assertEquals(expected, actual)
    }
}


private val jsonContent
    get() = """                                  
[
   {
      "id":1,
      "name":"Leanne Graham",
      "username":"Bret",
      "email":"Sincere@april.biz",
      "address":{
         "street":"Kulas Light",
         "suite":"Apt. 556",
         "city":"Gwenborough",
         "zipcode":"92998-3874",
         "geo":{
            "lat":"-37.3159",
            "lng":"81.1496"
         }
      },
      "phone":"1-770-736-8031 x56442",
      "website":"hildegard.org",
      "company":{
         "name":"Romaguera-Crona",
         "catchPhrase":"Multi-layered client-server neural-net",
         "bs":"harness real-time e-markets"
      }
   }
]
"""

private val user
    get() = UserNetworkModel(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        address = address,
        phone = "1-770-736-8031 x56442",
        company = company,
        website = "hildegard.org"
    )

private val address
    get() = AddressNetworkModel(
        street = "Kulas Light",
        suite = "Apt. 556",
        city = "Gwenborough",
        zipcode = "92998-3874",
        geolocation = geolocation
    )

private val geolocation
    get() = GeolocationNetworkModel(
        latitude = -37.3159,
        longitude = 81.1496
    )

private val company
    get() = CompanyNetworkModel(
        name = "Romaguera-Crona",
        catchPhrase = "Multi-layered client-server neural-net",
        businessStuff = "harness real-time e-markets"
    )