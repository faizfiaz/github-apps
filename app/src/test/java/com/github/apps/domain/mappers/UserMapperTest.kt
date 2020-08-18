package com.github.apps.domain.mappers

import com.github.apps.domain.entities.UserEntity
import com.github.apps.domain.exceptions.MapperException
import com.github.apps.domain.models.User
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit

class UserMapperTest {
    @Rule
    @JvmField
    var rule = MockitoJUnit.rule()

    @InjectMocks
    var userMapper: UserMapper? = null
    private var user: User? = null
    private var userEntity: UserEntity? = null

    @Before
    fun setUp() {
        user = User()
        user!!.id = 1
        userEntity = UserEntity()
        userEntity!!.id = 1
    }

    @Test
    fun createObjectValid() {
        Assert.assertNotNull(userMapper!!.createObject())
    }

    @Test
    fun createEntityValid() {
        Assert.assertNotNull(userMapper!!.createEntity())
    }

    @Test
    @Throws(MapperException::class)
    fun defineObjectValid() {
        Assert.assertSame(user, userMapper!!.defineObject(user))
    }

    @Test
    @Throws(MapperException::class)
    fun defineEntityValid() {
        Assert.assertSame(userEntity, userMapper!!.defineEntity(userEntity))
    }
}