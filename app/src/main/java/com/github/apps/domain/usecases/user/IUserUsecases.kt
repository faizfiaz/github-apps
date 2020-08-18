package com.github.apps.domain.usecases.user

import com.github.apps.data.remote.UserRepository
import com.github.apps.domain.exceptions.MapperException
import com.github.apps.domain.mappers.UserMapper
import com.github.apps.domain.models.User
import com.github.apps.domain.usecases.base.BaseUsecase
import io.reactivex.Single

abstract class IUserUsecases(mapper: UserMapper, userRepository: UserRepository?) :
        BaseUsecase<UserMapper, UserRepository>(mapper, userRepository) {

    @Throws(MapperException::class)
    abstract suspend fun getUser(q: String?, page: Int): Single<Any?>

}