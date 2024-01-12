package com.going.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NonDataBaseResponse<T>(
    @SerialName("status")
    val status: Int,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
)
