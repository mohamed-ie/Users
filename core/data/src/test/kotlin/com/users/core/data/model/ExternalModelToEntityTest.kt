package com.users.core.data.model

import com.users.core.model.Address
import com.users.core.model.Company
import com.users.core.model.Geolocation
import com.users.core.model.User
import org.junit.Assert.*
import org.junit.Test

class ExternalModelToEntityTest {

    @Test
    fun companyMapToEntity() {
        val company = company

        val entity = company.asEntity()

        assertEquals(company.name, entity.name)
        assertEquals(company.catchPhrase, entity.catchPhrase)
        assertEquals(company.businessStuff, entity.businessStuff)
    }

    @Test
    fun addressMapToEntity() {
        val address = address

        val entity = address.asEntity()

        assertEquals(address.street, entity.street)
        assertEquals(address.suite, entity.suite)
        assertEquals(address.city, entity.city)
        assertEquals(address.zipcode, entity.zipcode)
        assertEquals(address.geolocation.latitude, entity.latitude, 0.0)
        assertEquals(address.geolocation.longitude, entity.longitude, 0.0)
    }

    @Test
    fun userMapToEntity() {
        val user = user

        val entity = user.asEntity()

        assertEquals(user.id, entity.id)
        assertEquals(user.name, entity.name)
        assertEquals(user.username, entity.username)
        assertEquals(user.email, entity.email)
        assertEquals(user.phone, entity.phone)
        assertEquals(user.website, entity.website)
    }
}

private val user
    get() =  User(
        id = 1,
        name = "name",
        username = "username",
        email = "email",
        website = "website",
        phone = "phone",
        isLiked = true,
        address = address,
        company = company
    )

private val address
    get() = Address(
        street = "street",
        suite = "suite",
        city = "city",
        zipcode = "zipcode",
        geolocation = Geolocation(
            latitude = 1.0,
            longitude = 2.0
        )
    )

private val company
    get() =
        Company(
            name = "name",
            catchPhrase = "catchPhrase",
            businessStuff = "businessStuff"
        )
