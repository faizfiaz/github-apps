package com.github.apps.domain.entities

data class ErrorEntity(
        var resource: String? = null,
        var field: String? = null,
        var code: String? = null
)