package com.sopt.dive.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDataDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("message")
    val message: String
)

@Serializable
data class LoginResponseDto(
    @SerialName("success")
    val success: Boolean,

    @SerialName("code")
    val code: String,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: LoginDataDto?
)
