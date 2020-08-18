package com.github.apps.domain.usecases.user

import com.github.apps.data.local.PreferencesManager
import com.github.apps.data.remote.UserRepository
import com.github.apps.domain.entities.response.UserResponse
import com.github.apps.domain.mappers.UserMapper
import com.github.apps.domain.models.User
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

open class UserUsecases(mapper: UserMapper, repository: UserRepository?) : IUserUsecases(mapper, repository) {

    var preferencesManager: PreferencesManager? = PreferencesManager.instance

    override suspend fun getUser(q: String?, page: Int): Single<Any?> = withContext(Dispatchers.IO) {
        val response = async { repository?.searchUser(q, page) }
        try {
            checkResponse(response.await()?.blockingGet())
        } catch (e: Exception) {
            throw java.lang.Exception(e.message)
        }

    }

    fun checkResponse(response: UserResponse?): Single<Any?> {
        if (response?.errors != null) {
            return Single.just(response.message)
        }
        var listUser = mapper.convertToObjectList(response?.items!!)
        return Single.just(listUser)
    }

}