package com.github.apps.domain.mappers

import com.github.apps.domain.entities.UserEntity
import com.github.apps.domain.exceptions.MapperException
import com.github.apps.domain.models.User

class UserMapper : BaseMapper<UserEntity?, User?>() {
    override fun createObject(): User? {
        return User()
    }

    override fun createEntity(): UserEntity? {
        return UserEntity()
    }

    @Throws(MapperException::class)
    override fun defineObject(`object`: User?): User? {
        `object`?.avatar = baseEntity?.avatarUrl
        `object`?.name = baseEntity?.login
        `object`?.score = baseEntity?.score
        `object`?.url = baseEntity?.url
        return `object`
    }

    @Throws(MapperException::class)
    override fun defineEntity(entity: UserEntity?): UserEntity? {
        return entity
    }
}