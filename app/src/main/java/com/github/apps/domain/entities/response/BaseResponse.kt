package com.github.apps.domain.entities.response

import com.github.apps.domain.entities.BaseObjectEntity
import com.github.apps.domain.entities.ErrorEntity
import com.google.gson.annotations.SerializedName

open class BaseResponse<E> : BaseObjectEntity() {
    var data: E? = null

    var errors: List<ErrorEntity>? = null

    @SerializedName("documentation_url")
    var documentationUrl: String? = null

}


