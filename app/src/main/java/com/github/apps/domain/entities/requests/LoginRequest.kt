package com.github.apps.domain.entities.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @SerializedName("phoneno", alternate = ["email"])
        var identifier: String? = null,
        @SerializedName("password")
        var password: String? = null
)