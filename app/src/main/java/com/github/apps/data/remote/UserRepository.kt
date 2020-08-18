package com.github.apps.data.remote

import com.github.apps.domain.entities.response.UserResponse
import io.reactivex.Single

class UserRepository private constructor() : BaseRepository<UserResponse?>() {

    fun searchUser(q: String?, page: Int): Single<UserResponse?>? {
        return remoteAPI.login(q, page)
    }

    companion object {
        @JvmStatic
        var instance: UserRepository? = null
            get() {
                if (field == null) {
                    field = UserRepository()
                }
                return field
            }
            private set
    }

    override fun get(): Single<List<UserResponse?>?>? {
        return null
    }

    override fun getById(id: Int): Single<List<UserResponse?>?>? {
        return null
    }

}