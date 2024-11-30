package com.users.core.data.model

import com.users.core.test.model.address
import com.users.core.test.model.company
import com.users.core.test.model.user
import org.junit.Assert.assertEquals
import org.junit.Test

class ExternalModelToEntityTest {

    @Test
    fun companyMapToEntity() {
        val company = company()

        val entity = company.asEntity()

        assertEquals(company.name, entity.name)
        assertEquals(company.catchPhrase, entity.catchPhrase)
        assertEquals(company.businessStuff, entity.businessStuff)
    }

    @Test
    fun addressMapToEntity() {
        val address = address()

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
        val user = user()

        val entity = user.asEntity()

        assertEquals(user.id, entity.id)
        assertEquals(user.name, entity.name)
        assertEquals(user.username, entity.username)
        assertEquals(user.email, entity.email)
        assertEquals(user.phone, entity.phone)
        assertEquals(user.website, entity.website)
    }
}