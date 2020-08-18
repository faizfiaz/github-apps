package com.github.apps.domain.usecases.base

import com.github.apps.data.remote.BaseRepository
import com.github.apps.data.remote.UserRepository
import com.github.apps.domain.mappers.BaseMapper

abstract class BaseUsecase<M : BaseMapper<*, *>?, R :
BaseRepository<*>?>(protected var mapper: M, protected var repository: UserRepository?) {

    fun isErrorCode(statusCode: Int): Boolean {
        if (statusCode > 200) {
            return true
        }
        return false
    }
}